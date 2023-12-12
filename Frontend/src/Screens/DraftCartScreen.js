import { StyleSheet, Text, View, Button, Alert, ScrollView , TouchableOpacity, Modal} from 'react-native'
import Animated,{   useSharedValue,
  withSpring,
  useAnimatedStyle,
  Easing,
  withTiming,ReanimatedProvider} from 'react-native-reanimated';
import { CheckBox } from '@rneui/themed';
import React, { useState, useEffect } from 'react'
import PaymentModal from '../Modals/PaymentModal';
import { useNavigation } from '@react-navigation/native';
// import Animated, {
//   useSharedValue,
//   withSpring,
//   useAnimatedStyle,
//   Easing,
//   withTiming,
// } from 'react-native-reanimated';
const CartScreen = () => {

    const [modalVisible, setModalVisible] = useState(false);
    const [isPaymentModalVisible, setPaymentModalVisible] = useState(false);
    const [selectedRoom, setSelectedRoom] = useState(null);
    const [clientSecret, setClientSecret] = useState(false);
    const [selectedOptions, setSelectedOptions] = useState({
      breakfast: false,
      lunch: false,
      dinner: false,
    });

    const letterOpacities = Array.from({ length: 10 }, () => useSharedValue(0)); // Adjust the length based on the number of letters
    const letterOpacity = useSharedValue(0);
    const translateX = useSharedValue(-200);
    const translateY = useSharedValue(100);
    const [isRefundable, setIsRefundable] = useState(false);

    const rooms = [
      { id: 1, title: 'Room 1', price: 100 },
      { id: 2, title: 'Room 2', price: 120 },
      // Add more rooms as needed
    ];

    const navigation = useNavigation();

    const toggleOption = (option) => {
      setSelectedOptions((prevOptions) => ({
        ...prevOptions,
        [option]: !prevOptions[option],
      }));
    };
  
    const calculateTotalPrice = () => {
      
      return 100;
    };
  

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


      useEffect(() => {
        letterOpacities.forEach((letterOpacity, index) => {
          // Uncomment the line below to use a different animation effect (timing animation)
          // letterOpacities[index].value = withTiming(1, { duration: 300, delay: index * 100 }); // Adjust delay as needed
          letterOpacities[index].value = withSpring(1, { damping: 2, stiffness: 100, delay: index * 100 }); // Adjust delay as needed
        });
      }, []);
      const animatedStyles = letterOpacities.map((letterOpacity, index) => {
        return useAnimatedStyle(() => {
          return {
            opacity: letterOpacity.value,
            transform: [{ translateY: letterOpacity.value * 20 }], // Adjust the multiplier as needed
          };
        });
      });
    

    const proceedToCheckout = () => {
      // TO DO
      // call the backend API and send the reservationDTO, if Reservation was available 
      // the request wil contain the clientSecret of the paymentIntent + the reservationID
      // set the client secret with the one received from the backend
      // setClientSecret("clientSecret")
      setPaymentModalVisible(true)
    }
    const text = "Your Animated Text";
    return (
      
      <View style={styles.container}>
        <View style={styles.container}>
      <View style={styles.textContainer}>
        {text.split('').map((letter, index) => (
          <Animated.Text key={index} style={[styles.letter, animatedStyles[index]]}>
            {letter}
          </Animated.Text>
        ))}
      </View>
    </View>
        <ScrollView>
          {rooms.map((room) => (
            <TouchableOpacity
              key={room.id}
              style={styles.roomContainer}
              onPress={() => {
                setSelectedRoom(room);
                setModalVisible(true);
              }}
            >
              <Text>{room.title}</Text>
              <Text>Price: ${room.price}</Text>
            </TouchableOpacity>
          ))}
        </ScrollView>
  
        <Modal animationType="slide" transparent={false} visible={modalVisible}>
          <View style={styles.modalContainer}>
            <Text>{selectedRoom?.title}</Text>
            <Text>Price: ${selectedRoom?.price}</Text>
  
            <View style={styles.optionsContainer}>
              <Text>Options:</Text>
              <View style={styles.optionItem}>
                <CheckBox
                  value={selectedOptions.breakfast}
                  onValueChange={() => toggleOption('breakfast')}
                />
                <Text>Breakfast</Text>
                <Text>+$10</Text>
              </View>
              <View style={styles.optionItem}>
                <CheckBox value={selectedOptions.lunch} onValueChange={() => toggleOption('lunch')} />
                <Text>Lunch</Text>
                <Text>+$15</Text>
              </View>
              <View style={styles.optionItem}>
                <CheckBox value={selectedOptions.dinner} onValueChange={() => toggleOption('dinner')} />
                <Text>Dinner</Text>
                <Text>+$20</Text>
              </View>
            </View>
  
            <Text>Refundable?</Text>
            <CheckBox value={isRefundable} onValueChange={() => setIsRefundable(!isRefundable)} />
  
            <Text>Total Price: ${calculateTotalPrice()}</Text>
  
            <View>
              <Button title="Check Out" onPress={proceedToCheckout} />
              <PaymentModal isVisible={isPaymentModalVisible} onCancel={handleCheckoutCancellation} clientSecret={clientSecret} onSuccessfulPayment={handlePaymentSuccess}/>
            </View>

            <TouchableOpacity style={styles.closeButton} onPress={() => setModalVisible(false)}>
              <Text>Close</Text>
            </TouchableOpacity>
          </View>
        </Modal>
      </View>

    );
  };
  
  const styles = StyleSheet.create({
    container: {
      flex: 1,
      paddingTop: 50,
    },
    roomContainer: {
      backgroundColor: '#e0e0e0',
      padding: 20,
      margin: 10,
      borderRadius: 10,
    },
    modalContainer: {
      flex: 1,
      justifyContent: 'center',
      alignItems: 'center',
      padding: 20,
    },
    optionsContainer: {
      marginTop: 20,
    },
    optionItem: {
      flexDirection: 'row',
      alignItems: 'center',
    },
    checkoutButton: {
      backgroundColor: '#4CAF50',
      padding: 10,
      marginVertical: 20,
      borderRadius: 5,
    },
    closeButton: {
      backgroundColor: '#f44336',
      padding: 10,
      borderRadius: 5,
    },
    ButtonContainer:{
        backgroundColor: '#728FF3',
        alignSelf: 'center',
        marginTop: 30,
        width: 118,
        height: 43,
        borderRadius: 10,
        alignItems: 'center',
        justifyContent: 'center',
    },
  });

  //   return (
  //     <View>
  //       <Button title="Check Out" onPress={proceedToCheckout} />
  //       <PaymentModal isVisible={isPaymentModalVisible} onCancel={handleCheckoutCancellation} clientSecret={clientSecret} onSuccessfulPayment={handlePaymentSuccess}/>
  //     </View>
  //   );
  // };
  
  export default CartScreen;
  