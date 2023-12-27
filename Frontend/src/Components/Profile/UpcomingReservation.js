import React, { useState } from 'react';
import { View, Text, StyleSheet,FlatList } from 'react-native';
import ReservationCard from './ReservationCard';
import Color from '../../Styles/Color';
import Details from './Details';


const UpcomingReservation = () => {

  const reservationData = [
    { reservationID: '1', hotelName: 'Hotel A', roomTitle: 'Single Room', reservationDate: '2023-01-01', checkIn: '2023-02-01', checkOut: '2023-02-10', noOfRooms: 2, price: 200, invoice: "Reservation Details:\nReservation ID: 3\nCheck-in Date: 2024-01-04T12:00:00Z\nCheck-out Date: 2024-01-10T12:00:00Z\nRoom Details:\n\nStandard Room, 1 King Bed\nRoom price: 227\nnumber of rooms reserved: 3\nRoom prices after optional services\nRoom1: 300\t+Lunch: 73\n\t+Dinner: 141\nRoom prices after optional services\nRoom2: 300\t+Lunch: 73\n\t+Dinner: 141\nRoom prices after optional services\nRoom3: 300\t+Lunch: 73\n\t+Dinner: 141\nAdditional percentage due to refund option: +0%\nTotal price: 900" },
    { reservationID: '2', hotelName: 'Hotel B', roomTitle: 'Double Room', reservationDate: '2023-02-01', checkIn: '2023-03-01', checkOut: '2023-03-10', noOfRooms: 1, price: 150, invoice: 'Invoice B' },
    { reservationID: '3', hotelName: 'Hotel C', roomTitle: 'Triple Room', reservationDate: '2023-03-01', checkIn: '2023-04-01', checkOut: '2023-04-10', noOfRooms: 3, price: 300, invoice: 'Invoice C' },
    { reservationID: '4', hotelName: 'Hotel C', roomTitle: 'Triple Room', reservationDate: '2023-03-01', checkIn: '2023-04-01', checkOut: '2023-04-10', noOfRooms: 3, price: 300, invoice: 'Invoice C' },
    { reservationID: '5', hotelName: 'Hotel C', roomTitle: 'Triple Room', reservationDate: '2023-03-01', checkIn: '2023-04-01', checkOut: '2023-04-10', noOfRooms: 3, price: 300, invoice: 'Invoice C' },
    { reservationID: '6', hotelName: 'Hotel C', roomTitle: 'Triple Room', reservationDate: '2023-03-01', checkIn: '2023-04-01', checkOut: '2023-04-10', noOfRooms: 3, price: 300, invoice: 'Invoice C' },
    { reservationID: '7', hotelName: 'Hotel C', roomTitle: 'Triple Room', reservationDate: '2023-03-01', checkIn: '2023-04-01', checkOut: '2023-04-10', noOfRooms: 3, price: 300, invoice: 'Invoice C' },
    // Add more reservation data items as needed
  ];

  const [visible,setVisible]=useState(false);
  const [details,setDetails]=useState("");

  // Fetch data
  // const [reservationData, setReservationData] = useState([]);
  // useEffect(() => {
  //   const fetchData = async () => {
  //     try {
  //       const response = await ProfileAPI.getUpcomingReservations(authCtx.token);
  //     } catch (error) {
  //       console.log(error);
  //     }
  //   };
  //   fetchData();
  // }, []);

  const cancelReservation = (reservationId) =>{
    // TODO Cancellation
    console.log('====================================');
    console.log(reservationId);
    console.log('====================================');
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
      showDetails={()=>{
        setDetails(item.invoice);
        setVisible(true);
      }}
      cancelFunction={cancelReservation}
    />
  );

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