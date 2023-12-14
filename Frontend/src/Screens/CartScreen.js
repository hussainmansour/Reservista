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
  ActivityIndicator
} from "react-native";
import PaymentModal from "../Modals/PaymentModal";
import { useNavigation } from "@react-navigation/native";
import Collapsible from "react-native-collapsible";
import Icon from "react-native-vector-icons/Ionicons";
import Checkbox from "expo-checkbox";
import SmallButton from "../Components/SmallButton";
import AdditionalOptionsCollapse from "./RoomAdditionalOptionsCollapse.js.js";
import { reserve, rollBackReservation, verifyVoucher } from "../Utilities/UserAPI.js";

const CartScreen = ({route}) => {
  const navigation = useNavigation();
  const {price,title,count,roomDescriptionId,hotelID,refundable,fullyRefundableRate,checkIn,checkOut,foodOptions}=route.params;
  // these things will be passed as prpos
  // const foodOptions = { breakfastPrice: 21, lunchPrice: 57, dinnerPrice: 35 };
  // const reservationDetails = {
  //   price: 100,
  //   title: "Room title",
  //   count: 4,
  //   roomDescriptionId: "0002d331-89d7-4f19-b6bf-8cf553b767c5",
  //   hotelID:"001cc902-bea3-4381-86af-4064e3b90fc8",
  //   refundable:true,
  //   fullyRefundableRate: 15,
  //   checkIn: "2024-06-08T00:00:00Z",
  //   checkOut: "2024-06-09T00:00:00Z",

  // };

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
    // TO DO
    // navigate to the desired screen
    // navigation.navigate("");
  };

  const handleCheckoutCancellation =  async () => {
    setPaymentModalVisible(false);
    setClientSecret("");
    try {
      const response = await rollBackReservation(reservationId, setLoading);
    } catch (error) {
        console.error('Error occured during rolling back the reservation', error.message);
    } 
  };

  const toggleExpandedRoom = (roomId) => {
    setExpandedRooms((prevExpandedRooms) => ({
      ...prevExpandedRooms,
      [roomId]: !prevExpandedRooms[roomId],
    }));
  };

  const applyVoucher = async () => {
    
    try {
 
      const response = await verifyVoucher(`${voucherCode}`, setLoading);
      console.log(response);
      if (response.status === 200) {
        setDiscountRate(response.data);
        setIsVoucherApplied(true); 
        setExpandedVoucher(true);
         
      }else{
        setError(response.message)
        setIsVoucherApplied(false);
        setDiscountRate(0);
        setVoucherCode("");
      }
    } catch (error) {
        console.error('Error in verifying voucher', error.message);
        Alert.alert('Error', 'An error occurred while applying your voucher. Please try again.');
    } finally {
        setLoading(false);
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

    setLoading(true);
    try {
        const response = await reserve(reservationDTO, setLoading);
        console.log(response);
        if (response.status === 61) {
          setReservationId(response.data.reservationId);
          setClientSecret(response.data.clientSecret);
          setPaymentModalVisible(true);
           
        }else{
            Alert.alert('', response.message);
        }
    } catch (error) {
        console.error('Error in reservation', error.message);
        Alert.alert('Error', 'An error occurred during your reservation. Please try again.');
    } finally {
        setLoading(false);
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
            {refundable&& (
                <View style={styles.checkboxContainer}>
                <Text style={styles.fullRefundText}>Fully refundable (+{fullyRefundableRate}%)</Text>
                <Checkbox 
                value={isFullRefundApplied}
                style={styles.checkboxStyle} color="#45474B"
                onValueChange={() =>{
                    setFullRefundRate(fullyRefundableRate)
                    setIsFullRefundApplied(prev=>!prev)
                } } />
            </View>
            )}
      </View>
      
      {loading && <ActivityIndicator size="large" color="#0000ff" />}
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
    backgroundColor: "#f5f5f5",
    flex: 1,
    paddingTop: 50,
    justifyContent: "flex-start",
  },
  title: {
    fontSize: 32,
    fontWeight: "bold",
    color: "#ffffff",
    backgroundColor: "#5FBDFF",
    paddingHorizontal: 20,
    paddingVertical: 10,
    marginBottom: 20,
    padding:10
  },
  subtitle: {
    fontSize: 17,
    color: "#000000",
    paddingLeft: 20,
    paddingBottom: 15,
  },
  scrollViewContainer: {
    padding: 10,

  },
  roomContainer: {
    borderRadius: 5,
    marginBottom: 16,
    paddingHorizontal:15,
  },

  roomTitle: {
    fontSize: 18,
    fontWeight: "bold",
    color: "#0000000",
    
  },

  voucherContainr: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: 5,
    alignItems: "stretch",
    shadowColor: "#000",
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 3.84,
    elevation: 5,
  },
  textInputStyle: {
    flex: 2,
    backgroundColor: "#f5f5f5",
    padding: 8,
  },
  SmallbuttonStyle: {
    backgroundColor: "#45474B",
    flex: 1,
    padding: 8,
  },

  buttonStyle: {
    marginBottom: 30,
    width: "95%",
    height: 50,
    alignItems: "center",
    justifyContent: "center",
    borderRadius: 5,
    backgroundColor: "#5FBDFF",
    alignSelf: "center",
    shadowColor: "#000",
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 3.84,
    elevation: 5,
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
    color: "#000000",
    
  },

  discountText: {
    fontSize: 18,
    color: "#45474B", // grey
    
  },

  navyText: {
    fontSize: 22,
    fontWeight: "bold",
    color: "#45474B",
    marginBottom: 2,
    
  },
  fullRefundText: {
    fontSize: 18,
    fontWeight: "bold",
    color: "#45474B",
    marginBottom: 2,
    
  },
  calculationText:{
    fontSize: 18,
    color: "#45474B",
    marginBottom: 2,
  },

  applyVoucherText: {
    textDecorationLine: "underline",
    color: "#45474B",
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
    borderRadius: 10,
    padding: 16,
    marginBottom: 16,
    width: "95%",
    alignSelf: "center",
    borderColor: "#45474B",  // Border Color
    borderWidth: 2,         // Border Width
    backgroundColor: "#FFFFFF", // Background Color
    shadowColor: "#000",
    shadowOffset: {
        width: 0,
        height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 3.84,
    elevation: 5,
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
