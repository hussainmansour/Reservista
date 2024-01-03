// RoomCard Component
import { View, Text, TouchableOpacity, StyleSheet, FlatList, Image } from 'react-native';
import CustomizedButton from '../General/Buttons/CustomizedButton';
import cardStyles from "../../Styles/CardStyles";
import { getBaseURL } from '../../Utilities/New/BaseURL';


const RoomCard = ({ title, price, capacity, roomDetails, roomAvailability, id, imagesUrls, reservePress }) => {

    const filteredDetails = roomDetails.filter(detail => !detail.includes('Sleeps'));
    const renderItem = ({ item }) => (
        <Image style={cardStyles.roomImage} source={{ uri: item.replace("http://localhost:8080/", getBaseURL) }} />
    );

    console.log(imagesUrls);
    return (
        <View>

            <View style={cardStyles.CardContainer}>
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
                <View style={cardStyles.InfoContainer}>
                    <Text style={cardStyles.Title}>{title}</Text>
                    <Text>{`Price: ${price}$`}</Text>
                    <Text>{`Sleeps: ${capacity}`}</Text>
                    <Text>{`Details: ${filteredDetails}`}</Text>
                    <Text>{`Available Rooms: ${roomAvailability}`}</Text>
                    <CustomizedButton
                        text={"Reserve"}
                        onPress={() => {
                            reservePress(id, price, title,);
                        }}
                        textStyle={cardStyles.ButtonText}
                        buttonStyle={{ ...cardStyles.Button, width: '100%' }}
                    ></CustomizedButton>
                </View>
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        padding: 10,
    },
    card: {
        flexDirection: 'row',
        padding: 16,
        borderBottomWidth: 1,
        borderBottomColor: '#ccc',
    },
    imageContainer: {
        marginRight: 16,
    },
    image: {
        flex: 1,
        width: 200,
        height: 200,
        resizeMode: 'cover',
        borderRadius: 8,
        margin: 5
    },
    textContainer: {
        flex: 1,
    },
    hotelTitle: {
        fontSize: 20,
        fontWeight: 'bold',
        marginBottom: 5,
        color: '#333333',
    },
    rating: {
        color: 'gray',
    },
    stars: {
        color: 'gray',
    },
    price: {
        color: 'green',
        fontWeight: 'bold',
    },
    location: {
        color: 'gray',
    },
    button: {
        backgroundColor: '#3498db',
        padding: 10,
        alignItems: 'center',
        borderBottomLeftRadius: 10,
        borderBottomRightRadius: 10,
    },
    buttonText: {
        color: 'white',
        fontWeight: 'bold',
    },

});

export default RoomCard;
