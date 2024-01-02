import React, { useContext, useEffect, useState } from 'react';
import { View, Text, Image, Button, Modal, TouchableOpacity, ScrollView, StyleSheet, Alert, FlatList } from 'react-native';
import MapView, { Marker } from 'react-native-maps';
import Icon from 'react-native-vector-icons/FontAwesome';
import RoomCard from './RoomCard';
import RoomAPI from '../../Utilities/RoomsAPI';
import { useNavigation } from '@react-navigation/native';
import HotelImages from './HotelImages';
import { SearchCriteriaContext } from "../../Store/searchCriteriaContext";
import { SearchOptionsContext } from "../../Store/SearchOptionsContext";
import { SearchAndFilterAPI } from "../../Utilities/New/APIs/SearchAndFilterAPI";
import { getBaseURL } from '../../Utilities/New/BaseURL';
import Color from '../../Styles/Color';
import LoadingComponent from '../General/LoadingComponent';

const HotelView = ({ route, navigation }) => {

    const { updateSearchCriteria, ...searchCriteria } =
        useContext(SearchCriteriaContext);

    const [isModalVisible, setModalVisible] = useState(false);
    const [isLoading, setIsLoading] = useState(true);
    const [hotel, setHotel] = useState({});

    console.log('====================================');
    console.log(route.params.item);
    console.log('====================================');

    const {
        checkIn,
        checkOut,
        numberOfTravelers,
        numberOfRooms
    } = searchCriteria;

    useEffect(() => {
        const fetchData = async () => {
            const HotelDTO = {
                hotelId: route.params.item.id,
                numberOfRooms: numberOfRooms,
                numberOfTravelers: numberOfTravelers,
                checkIn: checkIn,
                checkOut: checkOut
            }
            let response = await SearchAndFilterAPI.getHotel(HotelDTO, (response) => {
                const responseBody = response.data;
                if (responseBody.data !== undefined) {
                    console.log(responseBody.data);
                    Alert.alert('Error', responseBody.data);
                }
                else {
                    console.log('====================================');
                    console.log(responseBody);
                    console.log('====================================');
                    Alert.alert("Error", "Failed to get Hotel");
                }
            });

            if (response !== undefined) {
                console.log("from hotel view");
                console.log('====================================');
                console.log(response);
                console.log('====================================');
                setHotel(response);
            }
        };

        fetchData().then(() => {
            setIsLoading(false);
        });
    }, []);

    // Wait until data is loaded before rendering
    if (isLoading) {
        return (
            <LoadingComponent></LoadingComponent>
        );
    }

    const {
        address,
        city,
        country,
        fullyRefundable,
        fullyRefundableRate,
        hotelFoodOptions,
        id,
        imagesUrls,
        name,
        rating,
        reviewCount,
        starRating,
        rooms
    } = hotel;

    console.log(hotel);

    const hotelTitle = name || "Hotel Name"; // You can replace "Hotel Name" with a default value

    const toggleModal = () => {
        setModalVisible(!isModalVisible);
    };
    navigation.setOptions({
        title: hotelTitle,
    });

    const reserve = (roomId, price, title) => {
        console.log("reserve");
        console.log(roomId);
        console.log(price);
        console.log(title);
        const Reservation = {
            price: price,
            title: title,
            count: numberOfRooms,
            roomDescriptionId: roomId,
            hotelID: id,
            refundable: fullyRefundable,
            fullyRefundableRate: fullyRefundableRate,
            checkIn: checkIn,
            checkOut: checkOut,
            foodOptions: hotelFoodOptions
        };
        console.log(Reservation);
        navigation.navigate('CartScreen', Reservation);
    }


    return (
        <ScrollView contentContainerStyle={styles.scrollContainer}
        showsVerticalScrollIndicator={false}
        >
            <View style={styles.titleContainer}>
                <Text style={styles.title}>{hotelTitle}</Text>
            </View>
            <FlatList
                horizontal
                data={imagesUrls}
                keyExtractor={(item, index) => index.toString()}
                renderItem={
                    ({ item }) => (
                        <Image style={styles.image} source={{ uri: item.replace("http://localhost:8080", getBaseURL) }} />
                    )
                }
                showsHorizontalScrollIndicator={false}
                contentContainerStyle={styles.imageContainer}
            />
            <View style={styles.container}>
                {/* Hotel Information */}
                {/* Hotel Name */}

                <View style={styles.infomapContainer}>

                    {/* Hotel Details (Rating, Reviews, Stars) */}
                    <View style={styles.detailsContainer}>
                        <View style={styles.starsContainer}>
                            <Icon name="star" size={20} color="#FFD700" />
                            <Text style={styles.starsText}>{` ${starRating} Stars`}</Text>
                        </View>
                        <View style={styles.ratingContainer}>
                            <Icon name="hotel" size={20} color="#333333" />
                            <Text style={styles.ratingText}>{` ${rating} Rating`}</Text>
                        </View>
                        <View style={styles.reviewsContainer}>
                            <Icon name="comments" size={20} color="#75C2F6" />
                            <Text style={styles.reviewText}>{`${reviewCount} Reviews`}</Text>
                        </View>
                        <TouchableOpacity onPress={toggleModal} style={{ ...styles.reviewButton, backgroundColor: Color.SEABLUE }}>
                            <Text style={styles.reviewButtonText}>See Reviews</Text>
                        </TouchableOpacity>
                    </View>

                    {/* Map and Address Container */}
                    <View style={styles.mapAndAddressContainer}>
                        {/* Map */}
                        {/*<MapView*/}
                        {/*    style={styles.map}*/}
                        {/*    initialRegion={{*/}
                        {/*        latitude,*/}
                        {/*        longitude,*/}
                        {/*        latitudeDelta: 0.0922,*/}
                        {/*        longitudeDelta: 0.0421,*/}
                        {/*    }}*/}
                        {/*>*/}
                        {/*    <Marker coordinate={{ latitude, longitude }} title={hotelTitle} />*/}
                        {/*</MapView>*/}

                        {/* Address */}
                        <Text style={styles.address}>{`Address: ${address}`}</Text>
                        <Text style={styles.address}>{`City: ${city}`}</Text>
                        <Text style={styles.address}>{`Country: ${country}`}</Text>

                    </View>
                </View>

                {/* Room Cards */}
                {/* <View style={styles.roomsContainer}>
                    {rooms.map((room, index) => (
                        <RoomCard key={index} {...room} reservePress={reserve} />
                    ))}
                </View> */}
                <FlatList data={rooms}
                    keyExtractor={(item, index) => index.toString()}
                    renderItem={({ item }) => (
                        <RoomCard {...item} reservePress={reserve} />
                    )}
                    showsVerticalScrollIndicator={false}
                    contentContainerStyle={styles.roomsContainer}
                >

                </FlatList>

                {/* Reviews Modal */}
                <Modal animationType="slide" transparent={false} visible={isModalVisible}>
                    <View style={styles.modalContainer}>
                        <Text style={styles.modalTitle}>Reviews Modal</Text>
                        {/* Add your review content here */}
                        <Button title="Close" onPress={toggleModal} />
                    </View>
                </Modal>
            </View>
        </ScrollView>
    );
};

const styles = StyleSheet.create({

    scrollContainer: {
        flexGrow: 1,
        backgroundColor: Color.PALEBLUE,
    },
    container: {
        flex: 1,
        backgroundColor: Color.PALEBLUE,
        paddingHorizontal: 10, // Adjusted padding
    },
    infoContainer: {
        flexDirection: 'column',
        alignItems: 'flex-start',
        marginBottom: 10, // Decreased space
    },
    name: {
        fontSize: 24,
        fontWeight: 'bold',
        color: '#333333',
        marginBottom: 5, // Decreased space
    },
    imageContainer: {
        backgroundColor: Color.PALEBLUE,
        flexDirection: 'row',
        margin:10,
        paddingRight:10,
        height: 400
    },
    image: {
        width: 300, // Adjust the width according to your preference
        height: 300, // Adjust the height according to your preference
        borderRadius: 10,
        marginHorizontal: 10, // Adjust the spacing between images
    },
    infomapContainer: {
        flexDirection: 'row',
        borderRadius: 15,
        padding: 10,
        overflow: 'hidden',
        backgroundColor: '#ffffff',
        elevation: 5,
        shadowColor: '#000',
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
    },
    detailsContainer: {
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        flex: 1
    },
    stars: {
        color: '#333333',
        fontSize: 40,
        marginRight: 5, // Decreased space
    },
    rating: {
        color: '#333333',
        fontSize: 40,
        marginRight: 5, // Decreased space
    },
    reviewButton: {
        backgroundColor: '#75C2F6',
        padding: 15,
        borderRadius: 10,
        elevation: 5,
        shadowColor: '#000',
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
    },
    reviewButtonText: {
        color: '#FFFFFF',
        fontWeight: 'bold',
        textAlign: 'center',
    },
    mapAndAddressContainer: {
        width: '50%',
        flexDirection: 'column',
        justifyContent: 'space-between',
    },
    map: {
        height: 150,
        width: '100%', // Adjusted width
        borderRadius: 10,
    },
    address: {
        width: '100%', // Adjusted width
        padding: 8, // Decreased space
        backgroundColor: '#ffffff',
        borderRadius: 5,
        fontWeight: 'bold',
        textAlign: 'center',
    },
    roomsContainer: {
        flex: 1,
        paddingBottom: 30,
        marginTop: 10,
         // Decreased space
    },
    modalContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#ffffff',
    },
    modalTitle: {
        fontSize: 20,
        fontWeight: 'bold',
        marginBottom: 10,
    },
    title: {
        fontSize: 24,
        fontWeight: 'bold',
        color: '#ffffff',

    },
    titleContainer: {
        flexDirection: 'row',
        height: '5%',
        borderRadius: 15,
        justifyContent: 'center',
        alignItems: 'center',
        overflow: 'hidden',
        backgroundColor: Color.SEABLUE,
        elevation: 5,
        shadowColor: '#000',
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
    },
    starsContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        marginBottom: 10,
    },
    starsText: {
        color: '#333333',
        fontSize: 20,
        marginLeft: 5,
    },
    ratingContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        marginBottom: 10,
    },
    ratingText: {
        color: '#333333',
        fontSize: 20,
        marginLeft: 5,
    },
    reviewsContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        marginBottom: 10,
    },
    reviewText: {
        color: '#333333',
        fontSize: 20,
        marginLeft: 5,
    },
});


export default HotelView;
