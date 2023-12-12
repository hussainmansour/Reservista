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
import SmallButton from "../Components/SmallButton";
import AdditionalOptionsCollapse from "./RoomAdditionalOptionsCollapse.js.js";

const CartScreen = () => {
  const navigation = useNavigation();

  // these things will be passed as prpos
  const foodOptions = { breakfastPrice: 21, lunchPrice: 57, dinnerPrice: 35 };
  const reservationDetails = {
    price: 100,
    title: "Room title",
    count: 4,
    roomDescriptionID: "roomTypeID",
    hotelID:"hotelID",
    refundable:true,
    fullyRefundableRate: 15,
    checkIn: "",
    checkOut: "",

  };

  const [isPaymentModalVisible, setPaymentModalVisible] = useState(false);
  const [clientSecret, setClientSecret] = useState("");
  const [expandedRooms, setExpandedRooms] = useState({});
  const [expandedVouncher, setExpandedVoucher] = useState(true);
  const [calculatedTotalPrice, setCalculatedTotalPrice] = useState(reservationDetails.count * reservationDetails.price);
  const [totalPriceAfterFullRefund, setTotalPriceAfterFullRefund] = useState(calculatedTotalPrice);
  const [totalPriceAfterDiscount, setTotalPriceAfterDiscount] =useState(calculatedTotalPrice);
  const [discountRate, setDiscountRate] = useState(0);
  const [voucherCode, setVoucherCode] = useState("");
  const [isVoucherApplied, setIsVoucherApplied] = useState(false);
  const [isFullRefundApplied, setIsFullRefundApplied] = useState(false);
  const [fullRefundRate, setFullRefundRate] = useState(0)
  const [error, setError] = useState("");
  const [rooms, setRooms] = useState(
    Array.from({ length: reservationDetails.count }, (_, index) => {
      const room = {
        id: index + 1,
        title: `Room ${index + 1}`,
        price: reservationDetails.price,
        hasBreakfast: false,
        hasLunch: false,
        hasDinner: false,
      };
      return room;
    })
  );

  useEffect(() => {
    
    const total = rooms.reduce((acc, room) => acc + room.price, 0);
    const refundRate = isFullRefundApplied? reservationDetails.fullyRefundableRate:0;
    const totalWithRefund = Math.ceil(total+ total*refundRate/100);
    const totalWithVoucher = (Math.ceil(totalWithRefund - totalWithRefund * (discountRate / 100)))

    setCalculatedTotalPrice(total);
    setTotalPriceAfterFullRefund(totalWithRefund);
    setTotalPriceAfterDiscount(totalWithVoucher);

  }, [rooms, isVoucherApplied, isFullRefundApplied]);

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

  const toggleBreakfast = (roomIndex) => {
    setRooms((prevRooms) => {
      prevRooms[roomIndex].hasBreakfast =
        prevRooms[roomIndex].hasBreakfast === false ? true : false;
      prevRooms[roomIndex].price += prevRooms[roomIndex].hasBreakfast
        ? foodOptions.breakfastPrice
        : -foodOptions.breakfastPrice;
      return [...prevRooms];
    });
  };

  const toggleDinner = (roomIndex) => {
    setRooms((prevRooms) => {
      prevRooms[roomIndex].hasDinner =
        prevRooms[roomIndex].hasDinner === false ? true : false;
      prevRooms[roomIndex].price += prevRooms[roomIndex].hasDinner
        ? foodOptions.dinnerPrice
        : -foodOptions.dinnerPrice;
      return [...prevRooms];
    });
  };

  const toggleLunch = (roomIndex) => {
    setRooms((prevRooms) => {
      prevRooms[roomIndex].hasLunch =
        prevRooms[roomIndex].hasLunch === false ? true : false;
      prevRooms[roomIndex].price += prevRooms[roomIndex].hasLunch
        ? foodOptions.lunchPrice
        : -foodOptions.lunchPrice;
      return [...prevRooms];
    });
  };

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

  const applyVoucher = () => {
    // call backend to validate voucher
    // success -> discount rate returned
    //            update is voucherApplied
    setDiscountRate(20);
    setIsVoucherApplied(true); // use effect will be called to update the total price
    setExpandedVoucher(true);
    // failure -> display error message
    // setError("error message")
  };

  const proceedToCheckout = () => {
    // TO DO
    // call the backend API and send the reservationDTO, if Reservation was available
    // the request wil contain the clientSecret of the paymentIntent + the reservationID
    // set the client secret with the one received from the backend

    const reservedRooms = rooms.map((room) => {
        const {hasBreakfast, hasLunch, hasDinner } = room;
        return {
          hasBreakfast,
          hasLunch,
          hasDinner,
        };
      });

    const reservationDTO = {
        hotelID: reservationDetails.hotelID,
        checkIn: reservationDetails.checkIn,
        checkOut: reservationDetails.checkOut,
        refundable: reservationDetails.refundable,
        voucherCode: voucherCode, 
        reservedRooms: reservedRooms,
        roomDescriptionID: reservationDetails.roomDescriptionID,
      };

    console.log(reservationDTO);

    setClientSecret(
      "pi_3OMCuZIpHzJgrvA93NbsgHP6_secret_IAkXyJpxKjdWXD2HRJFIaKagV"
    );
    setPaymentModalVisible(true);
  };

  return (
    <View style={styles.wholeForm}>
      <Text style={styles.title}>Room title</Text>

      <ScrollView contentContainerStyle={styles.scrollViewContainer}>
        {rooms.map((room) => (
          <View key={room.id} style={styles.roomContainer}>
            <AdditionalOptionsCollapse
              room={room}
              expanded={expandedRooms[room.id]}
              onToggle={() => toggleExpandedRoom(room.id)}
              onBreakfastToggle={() => toggleBreakfast(room.id - 1)}
              onLunchToggle={() => toggleLunch(room.id - 1)}
              onDinnerToggle={() => toggleDinner(room.id - 1)}
              foodOptions={foodOptions}
            />
          </View>
        ))}
      </ScrollView>

      <View style={styles.priceContainer}>
        <Text style={styles.totalPrice}>
          Total Price: ${calculatedTotalPrice}
        </Text>
        {isFullRefundApplied && (

            <Text style={styles.fullRefundText}>
              Price with full refundability: ${totalPriceAfterFullRefund}
            </Text>
            
            
        )}
        {isVoucherApplied && (
          <>
            <Text style={styles.fullRefundText}>
              Discount: {discountRate}% (-$
              {totalPriceAfterFullRefund - totalPriceAfterDiscount})
            </Text>
            {/* <Text style={styles.navyText}>
              Final Price: ${totalPriceAfterDiscount}
            </Text> */}
          </>
        )}
        {(isVoucherApplied || isFullRefundApplied) &&(
            <Text style={styles.navyText}>
                Final Price: ${totalPriceAfterDiscount}
            </Text>
        )}
        {isVoucherApplied && (
            <TouchableOpacity
                onPress={() => {
                setDiscountRate(0);
                setIsVoucherApplied(false);
                }}
            >
                <Text style={styles.applyVoucherText}>Remove voucher</Text>
            </TouchableOpacity>
        )}
        
          {!isVoucherApplied && (
            <TouchableOpacity
              onPress={() => setExpandedVoucher(!expandedVouncher)}
            >
              <Text style={styles.applyVoucherText}>Add voucher</Text>
            </TouchableOpacity>
          )}
          <Collapsible collapsed={expandedVouncher} duration={0}>
            <View style={styles.voucherBoxContainer}>
              <Text style={styles.navyText}>Enter Your Voucher</Text>
              <View style={styles.voucherContainr}>
                <TextInput
                  style={styles.textInputStyle}
                  placeholder="Voucher code"
                  onChangeText={(code) => setVoucherCode(code)}
                />
                <TouchableOpacity
                  style={styles.SmallbuttonStyle}
                  onPress={applyVoucher}
                >
                  <Text style={styles.whiteText}>APPLY</Text>
                </TouchableOpacity>
              </View>
              {error ? <Text style={styles.errorText}>{error}</Text> : null}
            </View>
          </Collapsible>
            {reservationDetails.refundable&& (
                <View style={styles.checkboxContainer}>
                <Text style={styles.fullRefundText}>fully refundable (+{reservationDetails.fullyRefundableRate}%)</Text>
                <Checkbox 
                value={isFullRefundApplied}
                style={styles.checkboxStyle} color="#131155"
                onValueChange={() =>{
                    setFullRefundRate(reservationDetails.fullyRefundableRate)
                    setIsFullRefundApplied(prev=>!prev)
                } } />
            </View>
            )}
            
        
      </View>
      

      <TouchableOpacity style={styles.buttonStyle} onPress={proceedToCheckout}>
        <Text style={styles.whiteText}>CHECK OUT</Text>
      </TouchableOpacity>

      <PaymentModal
        isVisible={isPaymentModalVisible}
        onCancel={handleCheckoutCancellation}
        clientSecret={clientSecret}
        onSuccessfulPayment={handlePaymentSuccess}
        price={totalPriceAfterDiscount}
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
    borderRadius: 5,
    marginBottom: 16,
    padding: 10,
  },

  roomTitle: {
    fontSize: 18,
    fontWeight: "bold",
    color: "#131155",
  },

  voucherContainr: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: 5,
    alignItems: "stretch",
  },
  textInputStyle: {
    flex: 2,
    backgroundColor: "#E0E5FF",
    padding: 8,
  },
  SmallbuttonStyle: {
    backgroundColor: "#131155",
    flex: 1,
    padding: 8,
  },

  buttonStyle: {
    marginBottom: 20,
    width: "95%",
    height: "5%",
    alignItems: "center",
    justifyContent: "center",
    borderRadius: 10,
    backgroundColor: "#131155",
    alignSelf: "center",
  },

  whiteText: {
    paddingTop: 5,
    color: "#FFFFFF",
    fontWeight: "bold",
    fontSize: 12,
    alignSelf: "center",
  },

  priceContainer: {
    padding: 16,
    marginTop: 10,
  },

  totalPrice: {
    fontSize: 32,
    fontWeight: "bold",
    color: "#131155",
  },

  discountText: {
    fontSize: 18,
    color: "#131155", // grey
  },

  navyText: {
    fontSize: 22,
    fontWeight: "bold",
    color: "#5F0F40",
    marginBottom: 2,
  },
  fullRefundText: {
    fontSize: 18,
    fontWeight: "bold",
    color: "#131155",
    marginBottom: 2,
  },

  applyVoucherText: {
    textDecorationLine: "underline",
    color: "#131155",
    fontSize: 16,
    marginBottom: 5,
    marginTop: 10,
    alignSelf: "flex-end",
    marginRight: 5,
  },
  errorText: {
    color: "red",
    fontSize: 12,
    marginTop: 1,
  },
  voucherBoxContainer: {
    borderWidth: 2,
    borderColor: "#131155",
    borderRadius: 10,
    padding: 16,
    marginBottom: 16,
    width: "95%",
    alignSelf: "center",
  },
  checkboxContainer: {
    flexDirection: "row",
    justifyContent: 'flex-end',
    alignItems: 'center',
    padding: 5,
  },
  checkboxStyle: {
    marginLeft: 10,
  },
  fullyRefundableContainer: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    padding: 4,
  },
});

export default CartScreen;
