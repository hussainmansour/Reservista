// RoomCard Component
import { View, Text, TouchableOpacity, StyleSheet,FlatList,Image } from 'react-native';



const RoomCard = ({ title, price, capacity, roomDetails, roomAvailability, id, reservePress }) => {

    const filteredDetails = roomDetails.filter(detail => !detail.includes('Sleeps'));

    return (
        <View style={styles.roomCardContainer}>
            <View style={styles.roomInfoContainer}>
                <Text style={styles.roomType}>{title}</Text>
                <Text>{`Price: ${price}$`}</Text>
                <Text>{`Sleeps: ${capacity}`}</Text>
                <Text>{`Details: ${filteredDetails}`}</Text>
                <Text>{`Available Rooms: ${roomAvailability}`}</Text>
                <TouchableOpacity style={styles.reserveButton} onPress={() => {
                    reservePress(id, price, title,);
                }}>
                    <Text style={styles.reserveButtonText}>Reserve x3</Text>
                </TouchableOpacity>
            </View>
        </View>
    );
};

// Styles
const styles = StyleSheet.create({
    roomCardContainer: {
        flexDirection: 'row',
        margin: 10,
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
    roomImagesContainer: {
        height: 150,
    },
    roomImage: {
        width: 150,
        height: 150,
        borderRadius: 15,
    },
    roomInfoContainer: {
        padding: 15,
        flex: 1,
    },
    roomType: {
        fontSize: 18,
        fontWeight: 'bold',
        marginBottom: 5,
        color: '#333333',
    },
    reserveButton: {
        backgroundColor: '#75C2F6',
        padding: 15,
        borderRadius: 10,
        marginTop: 10,
        elevation: 5,
        shadowColor: '#000',
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
    },
    reserveButtonText: {
        color: '#FFFFFF',
        fontWeight: 'bold',
        textAlign: 'center',
    },
});
export default RoomCard;
