import { StyleSheet, Text, View, Button, Alert } from 'react-native'
import React, { useState, useEffect } from 'react'
import PaymentModal from '../Modals/PaymentModal';
import { useNavigation } from '@react-navigation/native';

const CartScreen = () => {

    const [isPaymentModalVisible, setPaymentModalVisible] = useState(false);
    const [clientSecret, setClientSecret] = useState(false);
    const navigation = useNavigation();

    useEffect(() => {

      let timer;
     
      if (isPaymentModalVisible) {
        const timeoutDuration = 10 * 60 * 1000; // 10 minutes
        timer = setTimeout(() => {
          setPaymentModalVisible(false);
          Alert.alert("Sorry!", "Your checkout session has expired!");
        }, timeoutDuration); 
      }
    
      return () => {
        if (timer) {
          clearTimeout(timer);
        }
      };

    }, [isPaymentModalVisible]);
    
    const handlePaymentSuccess = () => {
      
      setPaymentModalVisible(false);
      Alert.alert("Reservation successful!","You can check your upcoming reservations now.")
      // TO DO
      // navigate to the desired screen
      // navigation.navigate("");
      
    };

    const handleCheckoutCancellation = () => {

      setPaymentModalVisible(false)
      setClientSecret("")
      // we will need to call the backend cancel API and send the reservation ID to cancel the reservation

    }

    const proceedToCheckout = () => {
      // TO DO
      // call the backend API and send the reservationDTO, if Reservation was available 
      // the request wil contain the clientSecret of the paymentIntent + the reservationID
      // set the client secret with the one received from the backend
      // setClientSecret("clientSecret")
      setPaymentModalVisible(true)
    }

    return (
      <View>
        <Button title="Check Out" onPress={proceedToCheckout} />
        <PaymentModal isVisible={isPaymentModalVisible} onCancel={handleCheckoutCancellation} clientSecret={clientSecret} onSuccessfulPayment={handlePaymentSuccess}/>
      </View>
    );
  };
  
  export default CartScreen;
  