// RoomCard Component
import React, { useState } from 'react';
import { View, Text, TouchableOpacity, StyleSheet, Modal } from 'react-native';
import CustomizedButton from '../General/Buttons/CustomizedButton';
import styles from '../../Styles/CardStyles';
import Color from '../../Styles/Color';




const ReservationCard = ({ reservationID, hotelName, roomTitle, reservationDate, checkIn, checkOut, noOfRooms, price, showDetails, cancelFunction }) => {

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
                <View style={{
                    flexDirection: 'row',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    marginTop: 10,
                }}>
                    <CustomizedButton
                        text={"More Details..."}
                        onPress={showDetails}
                        textStyle={styles.ButtonText}
                        buttonStyle={styles.Button}
                    ></CustomizedButton>
                    <CustomizedButton
                        text={"Cancel"}
                        onPress={()=>{
                            cancelFunction(reservationID);
                        }}
                        textStyle={styles.ButtonText}
                        buttonStyle={{...styles.Button,backgroundColor:Color.ORANGE}}
                    ></CustomizedButton>
                </View>
            </View>
        </View>
    );
};

export default ReservationCard;
