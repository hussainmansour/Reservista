import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, FlatList, Alert } from 'react-native';
import ReservationCard from './ReservationCard';
import Color from '../../Styles/Color';
import Details from './Details';
import cardStyles from '../../Styles/CardStyles';
import LoadingComponent from '../General/LoadingComponent';
import { ProfileAPI } from '../../Utilities/New/APIs/ProfileAPI';
import { CancellationAPI } from '../../Utilities/New/APIs/CancellationAPI';


const UpcomingReservation = () => {

  // const reservationData = [
  //   { reservationID: '1', hotelName: 'Hotel A', roomTitle: 'Single Room', reservationDate: '2023-01-01', checkIn: '2023-02-01', checkOut: '2023-02-10', noOfRooms: 2, price: 200, invoice: "Reservation Details:\nReservation ID: 3\nCheck-in Date: 2024-01-04T12:00:00Z\nCheck-out Date: 2024-01-10T12:00:00Z\nRoom Details:\n\nStandard Room, 1 King Bed\nRoom price: 227\nnumber of rooms reserved: 3\nRoom prices after optional services\nRoom1: 300\t+Lunch: 73\n\t+Dinner: 141\nRoom prices after optional services\nRoom2: 300\t+Lunch: 73\n\t+Dinner: 141\nRoom prices after optional services\nRoom3: 300\t+Lunch: 73\n\t+Dinner: 141\nAdditional percentage due to refund option: +0%\nTotal price: 900" },
  //   { reservationID: '2', hotelName: 'Hotel B', roomTitle: 'Double Room', reservationDate: '2023-02-01', checkIn: '2023-03-01', checkOut: '2023-03-10', noOfRooms: 1, price: 150, invoice: 'Invoice B' },
  //   { reservationID: '3', hotelName: 'Hotel C', roomTitle: 'Triple Room', reservationDate: '2023-03-01', checkIn: '2023-04-01', checkOut: '2023-04-10', noOfRooms: 3, price: 300, invoice: 'Invoice C' },
  //   { reservationID: '4', hotelName: 'Hotel C', roomTitle: 'Triple Room', reservationDate: '2023-03-01', checkIn: '2023-04-01', checkOut: '2023-04-10', noOfRooms: 3, price: 300, invoice: 'Invoice C' },
  //   { reservationID: '5', hotelName: 'Hotel C', roomTitle: 'Triple Room', reservationDate: '2023-03-01', checkIn: '2023-04-01', checkOut: '2023-04-10', noOfRooms: 3, price: 300, invoice: 'Invoice C' },
  //   { reservationID: '6', hotelName: 'Hotel C', roomTitle: 'Triple Room', reservationDate: '2023-03-01', checkIn: '2023-04-01', checkOut: '2023-04-10', noOfRooms: 3, price: 300, invoice: 'Invoice C' },
  //   { reservationID: '7', hotelName: 'Hotel C', roomTitle: 'Triple Room', reservationDate: '2023-03-01', checkIn: '2023-04-01', checkOut: '2023-04-10', noOfRooms: 3, price: 300, invoice: 'Invoice C' },
  //   // Add more reservation data items as needed
  // ];

  function getReservationById(reservationID) {
    return reservationData.find(reservation => reservation.reservationID === reservationID);
  }

  function deleteReservationById(reservationId) {
    return reservationData.filter(reservation => reservation.reservationID !== reservationId);
  }

  const [visible, setVisible] = useState(false);
  const [details, setDetails] = useState("");
  const [isLoading, setIsLoading] = useState(true);

  // Fetch data
  const [reservationData, setReservationData] = useState([]);
  useEffect(()=>{
    const fetchReservations=async()=>{
      let response = await ProfileAPI.getUpcomingReservation((response)=>{
        const responseBody=response.data;
        if(responseBody.data!==undefined){
          console.log(responseBody.data);
          Alert.alert('Error',responseBody.data);
        }
        else{
          console.log('====================================');
          console.log(responseBody);
          console.log('====================================');
          Alert.alert("Error", "Failed to get your reservations");
        }
      },setIsLoading);

      if(response!==undefined){
        console.log('====================================');
        console.log(response);
        console.log('====================================');
        setReservationData(response);
      }
    };
    fetchReservations();
  },[]);


  // Wait until data is loaded before rendering
  if (isLoading) {
    return (
      <LoadingComponent></LoadingComponent>
    );
  }

  // check if there is no reservation
  if(reservationData.length==0){
    return(
      <View style={styles.container}>
        <Text style={{...cardStyles.Title,fontSize:20}}>You have no upcoming reservations</Text>
      </View>
    );
  }



  const getRefundableAmoount= async(reservationId)=>{
    let response = await CancellationAPI.getRefundedAmount(reservationId,(response)=>{
      const responseBody=response.data;
      if(responseBody.data!==undefined){
        console.log(responseBody.data);
        Alert.alert('Error',responseBody.data);
      }
      else{
        console.log('====================================');
        console.log(responseBody);
        console.log('====================================');
        Alert.alert("Error", "Failed to cancel your reservation.\nTry again later.");
      }
    },setIsLoading);
    if(response!==undefined){
      console.log('====================================');
      console.log(response);
      console.log('====================================');
      return response;
    }
  }


  // final stage of cancellation
  const cancel = async(reservationID) =>{
    let response = await CancellationAPI.cancelReservation(reservationID,(response)=>{
      console.log("enter cancellation");
      const responseBody=response.data;
      if(responseBody.data!==undefined){
        console.log("responsebodydata");
        console.log(responseBody.data);
        Alert.alert('Error',responseBody.data);
      }
      else{
        console.log('====================================');
        console.log(responseBody);
        console.log('====================================');
        Alert.alert("Error", "Failed to cancel your reservation.\nTry again later.");
      }
    },setIsLoading);

    if(response!==undefined){
      console.log("response",response);
      setReservationData(deleteReservationById(reservationID));
      Alert.alert("Success","Reservation cancelled successfully");
    }
  }

  



  // start of the cancellation
  const cancelReservation = async (reservationId) => {
    console.log('====================================');
    console.log(reservationId);
    console.log('====================================');
    const refundableAmount=await getRefundableAmoount(reservationId);
    Alert.alert("Reservation Cancellation",`Are you sure you want to cancel you reservatin.\nThe amount of money you will recieve back is ${refundableAmount} $`,
    [
      {
        text: "Confirm",
        onPress:()=>{
          cancel(reservationId);
        }
      },
      {
        text: "Cancel",
        onPress:()=>{
          console.log("cancelled");
        }
      }
    ]
    );
  }

  const renderItem = ({ item }) => (
    <ReservationCard
      reservationID={item.reservationID}
      hotelName={item.hotelName}
      roomTitle={item.roomTitle}
      reservationDate={item.reservationDate}
      checkIn={item.checkIn}
      checkOut={item.checkOut}
      noOfRooms={item.noOfRooms}
      price={item.price}
      buttons={buttons}
    />
  );

  const buttons = [
    {
      text: 'More Details...',
      onPress: (id) => {
        console.log(id);
        setDetails(getReservationById(id).invoice);
        setVisible(true);
      },
    },
    {
      text: 'Cancel',
      onPress: (id) => cancelReservation(id),
      buttonStyle: { backgroundColor: Color.ORANGE },
    }
  ];

  return (
    <View style={styles.container}>
      <FlatList
        data={reservationData}
        renderItem={renderItem}
        showsVerticalScrollIndicator={false}
        keyExtractor={(item) => item.reservationID}
        contentContainerStyle={styles.flatListContainer}
      />

      <Details
        invoice={details}
        isVisible={visible}
        onClose={() => setVisible(false)}
      />
    </View>


  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
    backgroundColor: Color.PALEBLUE,
  },
  flatListContainer: {
    paddingBottom: 20,
  },
});


export default UpcomingReservation;