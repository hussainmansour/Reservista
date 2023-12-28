// RoomCard Component
import { View, Text, TouchableOpacity, StyleSheet,FlatList,Image } from 'react-native';
import CustomizedButton from '../General/Buttons/CustomizedButton';
import cardStyles from "../../Styles/CardStyles";


const RoomCard = ({ title, price, capacity, roomDetails, roomAvailability, id, reservePress }) => {

    const filteredDetails = roomDetails.filter(detail => !detail.includes('Sleeps'));

    return (
        <View style={cardStyles.CardContainer}>
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
                    buttonStyle={{...cardStyles.Button,width:'100%'}}
                ></CustomizedButton>
            </View>
        </View>
    );
};

export default RoomCard;
