import { useState, useEffect, useContext } from "react";
import { AuthContext } from '../../Store/authContext.js';
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
  ActivityIndicator
} from "react-native";
import PaymentModal from "./PaymentModal.js";
import { useNavigation } from "@react-navigation/native";
import Collapsible from "react-native-collapsible";
import Icon from "react-native-vector-icons/Ionicons";
import Checkbox from "expo-checkbox";
import AdditionalOptionsCollapse from "./RoomAdditionalOptionsCollapse.js.js";
import { reserve, rollBackReservation, verifyVoucher } from "../../Utilities/UserAPI.js";
import Color from "../../Styles/Color.js";
import CustomizedButton from "../General/Buttons/CustomizedButton.js";
import CustomTextInput from "../Inputs/CustomTextInput.js";
import styles from '../../Styles/ReservationStyles.js';
import { ReservationAPI } from "../../Utilities/New/APIs/ReservationAPI.js";

const CartScreen = ({route}) => {

  const navigation = useNavigation();
  const {price,title,count,roomDescriptionId,hotelID,refundable,fullyRefundableRate,checkIn,checkOut,foodOptions}=route.params;
  const [isPaymentModalVisible, setPaymentModalVisible] = useState(false);
  const [clientSecret, setClientSecret] = useState("");
  const [expandedRooms, setExpandedRooms] = useState({});
  const [expandedVouncher, setExpandedVoucher] = useState(true);
  const [calculatedTotalPrice, setCalculatedTotalPrice] = useState(count * price);
  const [totalPriceAfterFullRefund, setTotalPriceAfterFullRefund] = useState(calculatedTotalPrice);
  const [totalPriceAfterDiscount, setTotalPriceAfterDiscount] =useState(calculatedTotalPrice);
  const [discountRate, setDiscountRate] = useState(0);
  const [voucherCode, setVoucherCode] = useState("");
  const [isVoucherApplied, setIsVoucherApplied] = useState(false);
  const [isFullRefundApplied, setIsFullRefundApplied] = useState(false);
  const [fullRefundRate, setFullRefundRate] = useState(0)
  const [error, setError] = useState("");
  const [loading, setLoading] = useState('');
  const authCtx = useContext(AuthContext);
  const [reservationId, setReservationId] = useState("");
  const [rooms, setRooms] = useState(
      Array.from({ length: count }, (_, index) => {
        const room = {
          id: index + 1,
          title: `Room ${index + 1}`,
          price: price,
          hasBreakfast: false,
          hasLunch: false,
          hasDinner: false,
        };
        return room;
      })
  );

  useEffect(() => {

    const total = rooms.reduce((acc, room) => acc + room.price, 0);
    const refundRate = isFullRefundApplied? fullyRefundableRate:0;
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
  
    navigation.navigate('Home');
  };

  const handleCheckoutCancellation =  async () => {
    setPaymentModalVisible(false);
    setClientSecret("");
    let response = await ReservationAPI.rollback(reservationId, setLoading = setLoading)
   
  };

  const toggleExpandedRoom = (roomId) => {
    setExpandedRooms((prevExpandedRooms) => ({
      ...prevExpandedRooms,
      [roomId]: !prevExpandedRooms[roomId],
    }));
  };

  const applyVoucher = async () => {

    let response = await ReservationAPI.applyVoucher(voucherCode, (response)=>{

      const responseBody = response.data;

      if (responseBody.data !== undefined) {
          setError(responseBody.data)
          setIsVoucherApplied(false);
          setDiscountRate(0);
          setVoucherCode("");
      } else {
        
        console.log(responseBody)
      }

    }, setLoading); 

    // success
    if (response !== undefined) {
      setDiscountRate(response.data);
      setIsVoucherApplied(true);
      setExpandedVoucher(true);
  }

  };

  const proceedToCheckout = async () => {

    const reservedRooms = rooms.map((room) => {
      const {hasBreakfast, hasLunch, hasDinner } = room;
      return {
        hasBreakfast,
        hasLunch,
        hasDinner,
      };
    });

    const reservationDTO = {
      hotelID: hotelID,
      checkIn: checkIn,
      checkOut: checkOut,
      refundable: refundable,
      voucherCode: voucherCode==""? null:voucherCode,
      reservedRooms: reservedRooms,
      roomDescriptionId: roomDescriptionId,
    };

    console.log(reservationDTO);

    let response = await ReservationAPI.reserve(reservationDTO,(response) =>{
        
        const responseBody = response.data;

        if (responseBody.data !== undefined){
          Alert.alert('Error', responseBody.data);
        }
        else{
          console.log(responseBody.data);
        }

    }, setLoading);

    if (response !== undefined){
      setReservationId(response.reservationId);
      setClientSecret(response.clientSecret);
      setPaymentModalVisible(true);
    }

  };

  return (
      <View style={styles.wholeForm}>
        <Text style={styles.title}>{title}</Text>

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

              <Text style={styles.calculationText}>
                Full refundability: {fullyRefundableRate}% (+${totalPriceAfterFullRefund - calculatedTotalPrice})
              </Text>
          )}
          {isVoucherApplied && (
              <>
                <Text style={styles.calculationText}>
                  Discount: {discountRate}% (-$
                  {totalPriceAfterFullRefund - totalPriceAfterDiscount})
                </Text>
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
              <Text style={styles.fullRefundText}>Enter Your Voucher</Text>
              <View style={styles.voucherContainer}>
                <TextInput
                    style={styles.textInputStyle}
                    placeholder="Voucher code"
                    onChangeText={(code) => setVoucherCode(code)}
                />
            
                  <CustomizedButton
                    text={"APPLY"}
                    onPress={applyVoucher}
                    buttonStyle={styles.SmallbuttonStyle} 
                    textStyle={styles.whiteText} 
                  />
              
              </View>
              {error ? <Text style={styles.errorText}>{error}</Text> : null}
            </View>
          </Collapsible>
          {refundable&& (
              <View style={styles.checkboxContainer}>
                <Text style={styles.fullRefundText}>Fully refundable (+{fullyRefundableRate}%)</Text>
                <Checkbox
                    value={isFullRefundApplied}
                    style={styles.checkboxStyle} color= {Color.MIDNIGHTBLUE} 
                    onValueChange={() =>{
                      setFullRefundRate(fullyRefundableRate)
                      setIsFullRefundApplied(prev=>!prev)
                    } } />
              </View>
          )}
        </View>

        {loading && <ActivityIndicator size="large" color= {Color.MIDNIGHTBLUE} />}
        <CustomizedButton
                    text={"CHECK OUT"}
                    onPress={proceedToCheckout}
                    buttonStyle={styles.buttonStyle} 
                    textStyle={styles.whiteText} 
                  />


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


export default CartScreen;
