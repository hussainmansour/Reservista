import { StyleSheet, Text, View, Button, Alert } from 'react-native'
import React, { useState, useEffect } from 'react'
import PaymentModal from '../Modals/PaymentModal';
import { useNavigation } from '@react-navigation/native';

const MockCartScreen = () => {

    const [isPaymentModalVisible, setPaymentModalVisible] = useState(false);
    const [clientSecret, setClientSecret] = useState(false);
    const navigation = useNavigation();

    useEffect(() => {

      let timer;
      const clearTimer = () => {
        if (timer) {
          clearTimeout(timer);
        }
      };

      if (isPaymentModalVisible) {
        const timeoutDuration = 10 * 60 * 1000;
        timer = setTimeout(() => {
          setPaymentModalVisible(false);
          console.log("expireddddd")
          Alert.alert("Sorry!", "Your checkout session has expired!");
        }, timeoutDuration); 
      }
    
      return () => {
        clearTimer();
      };

    }, [isPaymentModalVisible]);
    
    const handlePaymentSuccess = () => {
      
      
      setPaymentModalVisible(false);
      Alert.alert("Reservation successful!","You can check your upcoming reservations now.")

      // navigate to the home screen
      navigation.navigate("VerificationCode");
      
    };

    const handleCheckoutCancellation = () => {

      // for debugging
      console.log("checkout cancelled")
      setPaymentModalVisible(false)
      setClientSecret("")
      // we will need to call the backend cancel API and send the reservation ID to cancel the reservation

    }

    const proceedToCheckout = () => {
      // call the backend API and send the reservationDTO, if Reservation was available 
      // the request wil contain the clientSecret of the paymentIntent + the reservationID
      // set the client secret with the one received from the backend
      setClientSecret("pi_3OKvd1IpHzJgrvA92elWrBVc_secret_yPPrx4kU6b37uYjdWXadI9Xr2")
      setPaymentModalVisible(true)
    }

    return (
      <View>
        <Button title="Check Out" onPress={proceedToCheckout} />
        <PaymentModal isVisible={isPaymentModalVisible} onCancel={handleCheckoutCancellation} clientSecret={clientSecret} onSuccessfulPayment={handlePaymentSuccess}/>
      </View>
    );
  };
  
  export default MockCartScreen;
  