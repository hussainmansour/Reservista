import { useState, useEffect } from "react";
import {
  StyleSheet,
  Text,
  View,
  Button,
  Alert,
  ScrollView,
  TouchableOpacity,
  ImageBackground,
  TextInput,
  
} from "react-native";
import PaymentModal from "../Modals/PaymentModal";
import { useNavigation } from "@react-navigation/native";
import Collapsible from "react-native-collapsible";
import Icon from "react-native-vector-icons/Ionicons";
import Checkbox from "expo-checkbox";
import SmallButton from '../Components/SmallButton';

const CartScreen = () => {

  const foodOptions = { breakfastPrice: 21, lunchPrice: 57, dinnerPrice: 35 };
  const roomDetails = { price: 100, title: "Room title", count: 4, roomTypeID: "roomTypeID"}

  const [isPaymentModalVisible, setPaymentModalVisible] = useState(false);
  const [clientSecret, setClientSecret] = useState("");
  const [expandedRooms, setExpandedRooms] = useState({});
  const [expandedVouncher, setExpandedVoucher] = useState(true);
  const [rooms, setRooms] = useState( Array.from({ length: roomDetails.count }, (_, index) => {
      const room = {
        id: index + 1,
        title: `Room ${index + 1}`,
        price: roomDetails.price,
        isBreakfastSelected: false,
        isLunchSelected: false,
        isDinnerSelected: false,
      };
      return room;
    })
  )

  const toggleBreakfast = (roomIndex)=>{
  
    setRooms ((prevRooms)=>{
      prevRooms[roomIndex].isBreakfastSelected = prevRooms[roomIndex].isBreakfastSelected === false ? true : false;
      prevRooms[roomIndex].price += prevRooms[roomIndex].isBreakfastSelected
      ? foodOptions.breakfastPrice
      : -foodOptions.breakfastPrice;
      return [...prevRooms];
    })
  }
  const toggleDinner = (roomIndex)=>{
    
    setRooms ((prevRooms)=>{
      prevRooms[roomIndex].isDinnerSelected = prevRooms[roomIndex].isDinnerSelected === false ? true : false;
      prevRooms[roomIndex].price += prevRooms[roomIndex].isDinnerSelected
      ? foodOptions.dinnerPrice
      : -foodOptions.dinnerPrice;
      return [...prevRooms];
    })
  }

  const toggleLunch = (roomIndex)=>{
    
    setRooms ((prevRooms)=>{
      prevRooms[roomIndex].isLunchSelected = prevRooms[roomIndex].isLunchSelected === false ? true : false;
      prevRooms[roomIndex].price += prevRooms[roomIndex].isLunchSelected
      ? foodOptions.lunchPrice
      : -foodOptions.lunchPrice;
      return [...prevRooms];
    })
  }


  // useEffect(() =>{


  // }, [rooms]);

  const navigation = useNavigation();

  // will be sent as prop
  // title
  // roomTypeID
  // number of rooms
  // price of room
  // price if breakfast, lunch, dinner
  // I will need to iitialize an array with the price of each room to update the price after addding additional options

  // const rooms = [
  //   { id: 1, title: "Room 1", price: 100 },
  //   { id: 2, title: "Room 2", price: 120 },
  //   { id: 3, title: "Room 1", price: 100 },
  //   { id: 4, title: "Room 1", price: 100 },
  // ];

  


  // const rooms = Array.from({ length: roomDetails.count }, (_, index) => {
  //   const room = {
  //     id: index + 1,
  //     title: `Room ${index + 1}`,
  //     price: roomDetails.price,
  //     isBreakfastSelected: 0,
  //     isLunchSelected: 0,
  //     isDinnerSelected: 0,
  //   };
  
  //   return room;
  // });

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
    Alert.alert(
      "Reservation successful!",
      "You can check your upcoming reservations now."
    );
    // TO DO
    // navigate to the desired screen
    // navigation.navigate("");
  };

  const handleCheckoutCancellation = () => {
    setPaymentModalVisible(false);
    setClientSecret("");
    // we will need to call the backend cancel API and send the reservation ID to cancel the reservation
  };

  const toggleExpandedRoom = (roomId) => {
    setExpandedRooms((prevExpandedRooms) => ({
      ...prevExpandedRooms,
      [roomId]: !prevExpandedRooms[roomId],
    }));
  };

  const proceedToCheckout = () => {
    // TO DO
    // call the backend API and send the reservationDTO, if Reservation was available
    // the request wil contain the clientSecret of the paymentIntent + the reservationID
    // set the client secret with the one received from the backend
    // setClientSecret("clientSecret")
    setPaymentModalVisible(true);
  };

  return (
    <View style={styles.wholeForm}>
      <Text style={styles.title}>Room title</Text>
      
      <ScrollView contentContainerStyle={styles.scrollViewContainer}>

        {rooms.map((room) => (
          <View key={room.id} style={styles.roomContainer}>
            <TouchableOpacity
              onPress={() => {
                toggleExpandedRoom(room.id);
              }}
            >
              <View style={styles.roomHeader}>
                <Icon
                  name={
                    expandedRooms[room.id]
                      ? "chevron-up-outline"
                      : "chevron-down-outline"
                  }
                  size={24}
                  color="#131155"
                />
                <Text style={styles.roomTitle}>{room.title}</Text>
                <Text>${room.price}</Text>
              </View>
            </TouchableOpacity>

            <Collapsible collapsed={!expandedRooms[room.id]}>
              <View>
                <View style={styles.horizontalLine} />
                <Text style={styles.additionalOptionsLabel}>
                  Additional Food Options:
                </Text>
                <View style={styles.foodInfoContainer}>
                  <View style={styles.textContainer}>
                    <Text>Breakfast</Text>
                  </View>
                  <View style={styles.checkboxContainer}>
                    <Text>(+${foodOptions.breakfastPrice})</Text>
                    <Checkbox 
                    style={styles.checkboxStyle}
                    value={room.isBreakfastSelected}
                    color= "#131155"
                    onValueChange={() => toggleBreakfast(room.id-1)} />  
                  </View>
                </View>
                <View style={styles.foodInfoContainer}>
                  <View style={styles.textContainer}>
                    <Text>Lunch</Text>
                  </View>
                  <View style={styles.checkboxContainer}>
                    <Text>(+${foodOptions.lunchPrice})</Text>
                    <Checkbox 
                    style={styles.checkboxStyle}
                    value={room.isLunchSelected}
                    color= "#131155"
                    onValueChange={() => toggleLunch(room.id-1)} />  
                  </View>
                </View>
                <View style={styles.foodInfoContainer}>
                  <View style={styles.textContainer}>
                    <Text>Dinner</Text>
                  </View>
                  <View style={styles.checkboxContainer}>
                    <Text>(+${foodOptions.dinnerPrice})</Text>
                    <Checkbox 
                    style={styles.checkboxStyle}
                    value={room.isDinnerSelected}
                    color= "#131155"
                    onValueChange={() => toggleDinner(room.id-1)} />  
                  </View>
                </View>
              </View>
            </Collapsible>
          </View>
        ))}
        <View>
          <TouchableOpacity onPress={() => {
                setExpandedVoucher(!expandedVouncher)
              }}>
            <Text style={styles.appliedVoucherText}>add voucher</Text>
          </TouchableOpacity>
          <Collapsible collapsed={expandedVouncher}>
            <View style={styles.voucherContainr}>
            <TextInput
              style={styles.input}
              placeholder="Voucher code"
            />
            <TouchableOpacity style={styles.SmallbuttonStyle} onPress={proceedToCheckout}>
              <Text style={styles.testModeText}>APPLY</Text>
            </TouchableOpacity>
          </View>
        </Collapsible>
        </View>

      </ScrollView>
      <View style={styles.priceContainer}>
        <Text style={styles.totalPrice}>Total Price: total_price</Text>

        {true && (
          <>
            <Text style={styles.discountText}>Discount: 20%</Text>
            <Text style={styles.discountedPriceText}>After Discount: calculated price</Text>
          </>
        )}
      </View>

      <TouchableOpacity style={styles.buttonStyle} onPress={proceedToCheckout}>
        <Text style={styles.testModeText}>CHECK OUT</Text>
      </TouchableOpacity>

      <PaymentModal
        isVisible={isPaymentModalVisible}
        onCancel={handleCheckoutCancellation}
        clientSecret={clientSecret}
        onSuccessfulPayment={handlePaymentSuccess}
      />
      
    </View>
    
  );
};

const styles = StyleSheet.create({
  wholeForm: {
    backgroundColor: "#CDD2FF",
    flex: 1,
    paddingTop: 50,
    justifyContent: "flex-start",
    // paddingHorizontal: 20,
  },
  title: {

    fontSize: 28,
    fontWeight: "bold",
    color: "#ffffff",
    backgroundColor: "#131155",
    paddingLeft: 20,
    // includeFontPadding:10,
  },
  subtitle: {
    fontSize: 17,
    color: "#131155",
    paddingLeft: 20,
    paddingBottom: 15,
  },
  scrollViewContainer: {
    padding: 16,
  },
  roomContainer: {
    backgroundColor: "#E0E5FF",
    borderRadius: 10,
    marginBottom: 16,
    padding: 16,
  },
  roomHeader: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
  },
  icon: {
    marginRight: 8,
  },
  roomTitle: {
    fontSize: 18,
    fontWeight: "bold",
    color: "#131155",
  },
 
  foodInfoContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 5,
  },
  voucherContainr:{
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    // padding:20,
  },
  input: {
    flex: 2,
    backgroundColor: '#E0E5FF', // Same background color as foodInfoContainer
    borderRadius: 10, // Same border radius as foodInfoContainer
    padding: 8,
    marginRight: 5,
  },
  textContainer: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  checkboxContainer: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  horizontalLine: {
    borderBottomColor: "#131155",
    borderBottomWidth: 1,
    marginVertical: 8,
  },
  additionalOptionsLabel: {
    fontSize: 16,
    fontWeight: "bold",
    color: "#131155",
    marginBottom: 8,
    // textDecorationLine: 'underline',
  },
  buttonStyle: {
    // padding: 3/,
    marginBottom: 20,
    // paddingHorizontal:5,
    width:"95%",
    height: "5%",
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: 10,
    backgroundColor: "#131155",
    alignSelf: 'center',
  },
  SmallbuttonStyle: {

    backgroundColor: "#131155",
    flex: 1,

    borderRadius: 10, // Same border radius as foodInfoContainer
    padding: 8,
    marginRight: 8,
  },
  testModeText: {
    paddingTop: 5,
    color: "#CDD2FF",
    fontWeight: "bold",
    fontSize: 12,
    alignSelf: "center",
  },
  checkboxStyle: {
    marginLeft:10,
  },
  buttonText:{
    fontSize: 20,
    color: 'white',
    marginBottom:8,
},
priceContainer: {
  padding: 16,
  marginTop: 10,
},

totalPrice: {
  fontSize: 24,
  fontWeight: 'bold',
  color: '#131155', 
},

discountText: {
  fontSize: 18,
  color: '#555', // grey
},

discountedPriceText: {
  fontSize: 18,
  fontWeight: 'bold',
  color: '#131155', 
},


appliedVoucherText: {
  textDecorationLine: 'underline',
  color: '#131155',
  fontSize: 16,
  marginBottom: 8,
  alignSelf: 'flex-end',
  marginRight:15,
},
});

export default CartScreen;
