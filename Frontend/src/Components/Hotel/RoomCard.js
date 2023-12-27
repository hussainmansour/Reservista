// RoomCard Component
import { View, Text, TouchableOpacity, StyleSheet,FlatList,Image } from 'react-native';
import CustomizedButton from '../General/Buttons/CustomizedButton';
import styles from "../../Styles/CardStyles";


const RoomCard = ({ title, price, capacity, roomDetails, roomAvailability, id, reservePress }) => {

    const filteredDetails = roomDetails.filter(detail => !detail.includes('Sleeps'));

    return (
        <View style={styles.CardContainer}>
            <View style={styles.InfoContainer}>
                <Text style={styles.Title}>{title}</Text>
                <Text>{`Price: ${price}$`}</Text>
                <Text>{`Sleeps: ${capacity}`}</Text>
                <Text>{`Details: ${filteredDetails}`}</Text>
                <Text>{`Available Rooms: ${roomAvailability}`}</Text>
                <CustomizedButton
                    text={"Reserve"}
                    onPress={() => {
                        reservePress(id, price, title,);
                    }}
                    textStyle={styles.ButtonText}
                    buttonStyle={styles.Button}
                ></CustomizedButton>
            </View>
        </View>
    );
};

export default RoomCard;
