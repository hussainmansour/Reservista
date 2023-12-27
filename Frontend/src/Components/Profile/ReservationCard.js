import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import CustomizedButton from '../General/Buttons/CustomizedButton';
import styles from '../../Styles/CardStyles';
import Color from '../../Styles/Color';

const ReservationCard = ({
    reservationID,
    hotelName,
    roomTitle,
    reservationDate,
    checkIn,
    checkOut,
    noOfRooms,
    price,
    buttons,
}) => {
    return (
        <View style={styles.CardContainer}>
            <View style={styles.InfoContainer}>
                <Text style={styles.Title}>{`Reservation number: ${reservationID}`}</Text>
                <Text>{`Hotel : ${hotelName}$`}</Text>
                <Text>{`Room type: ${roomTitle}`}</Text>
                <Text>{`Reservation date: ${reservationDate}`}</Text>
                <Text>{`Checkin date: ${checkIn}`}</Text>
                <Text>{`Checkout date: ${checkOut}`}</Text>
                <Text>{`Number of rooms: ${noOfRooms}`}</Text>
                <Text>{`Total price: ${price}`}</Text>
                <View style={additionalStyles.buttonsContainer}>
                    {buttons.map((button, index) => (
                        <CustomizedButton
                            key={index}
                            text={button.text}
                            onPress={() => button.onPress(reservationID)}
                            textStyle={{...styles.ButtonText,...button.textStyle}}
                            buttonStyle={{...styles.Button,...button.buttonStyle}}
                        />
                    ))}
                </View>
            </View>
        </View>
    );
};

export default ReservationCard;

const additionalStyles = StyleSheet.create({
    buttonsContainer: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        marginTop: 10,
    },
});
