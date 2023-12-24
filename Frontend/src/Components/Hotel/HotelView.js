import React, { useEffect, useState } from 'react';
import { View, Text, Image, Button, Modal, TouchableOpacity, ScrollView, StyleSheet } from 'react-native';
import MapView, { Marker } from 'react-native-maps';
import Icon from 'react-native-vector-icons/FontAwesome';
import RoomCard from '../Components/Cards/RoomCard';
import RoomAPI from '../../Utilities/RoomsAPI';
import { useNavigation } from '@react-navigation/native';
import HotelImages from '../Components/HotelImages';

const HotelView = ({ route }) => {

    const {item,searchDTO}=route.params;
    const{address,city,country,fullyRefundable,fullyRefundableRate,hotelFoodOptions,id,images,minRoomPrice,name,rating,reviewCount,starRating}=item;
    const{checkIn,checkOut,numberOfTravelers,numberOfRooms}=searchDTO;


    const hotelTitle = name || "Hotel Name"; // You can replace "Hotel Name" with a default value

    const [isModalVisible, setModalVisible] = useState(false);


    const [rooms,setRooms]=useState([])

    const toggleModal = () => {
        setModalVisible(!isModalVisible);
    };

    const navigation = useNavigation();

    const reserve = (roomId,price,title) =>{
        console.log("reserve");
        console.log(roomId);
        console.log(price);
        console.log(title);
        const Reservation={
            price: price,
            title: title,
            count: numberOfRooms,
            roomDescriptionId: roomId,
            hotelID:id,
            refundable:fullyRefundable,
            fullyRefundableRate: fullyRefundableRate,
            checkIn: checkIn,
            checkOut: checkOut,
            foodOptions : hotelFoodOptions
        };
        console.log(Reservation);
        navigation.navigate('CartScreen',Reservation);
    }

    const HotelDTO = {
        "hotelId": id,
        "numberOfRooms": numberOfRooms,
        "numberOfTravelers": numberOfTravelers,
        "checkIn": checkIn,
        "checkOut": checkOut
    }
    console.log("from hotel")
    console.log(HotelDTO);



    // Getting the profile
    useEffect(() => {
        const fetchRooms = async () => {
            try {
                const response = await RoomAPI.getRooms(HotelDTO);
                console.log(response);
                setRooms(response.roomDTOList);
            } catch (error) {
                console.log('Error fetching data:', error);
            }
        };

        fetchRooms();
    }, []);

    console.log("from rooms");
    console.log(rooms);

    return (
        <ScrollView contentContainerStyle={styles.scrollContainer}>
            <View style={styles.container}>

                {/* Hotel Information */}

                <HotelImages></HotelImages>
                {/* Hotel Name */}
                <View style={styles.titleContainer}>
                    <Text style={styles.title}>{hotelTitle}</Text>
                </View>

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
                        <TouchableOpacity onPress={toggleModal} style={styles.reviewButton}>
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
                <View style={styles.roomsContainer}>
                    {rooms.map((room, index) => (
                        <RoomCard key={index} {...room} reservePress={reserve} />
                    ))}
                </View>

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
    },
    container: {
        flex: 1,
        paddingTop: 40,
        backgroundColor: '#f5f5f5',
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
        flexDirection: 'row',
        marginTop: 20,
        height: 200
    },
    image: {
        width: 150, // Adjust the width according to your preference
        height: 150, // Adjust the height according to your preference
        borderRadius: 10,
        marginRight: 10, // Adjust the spacing between images
    },
    infomapContainer: {
        flexDirection: 'row',
        borderRadius: 15,
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
    },
    roomsContainer: {
        flex: 1,
        paddingBottom: 30,
        marginTop: 10, // Decreased space
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
        color: '#333333',

    },
    titleContainer: {
        flexDirection: 'row',
        margin: 10,
        height: '5%',
        borderRadius: 15,
        justifyContent: 'center',
        alignItems: 'center',
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
