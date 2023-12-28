import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const UpcomingReservation = () => {
  // Dummy data for illustration purposes
  const upcomingReservations = [
    {
      id: '1',
      guestName: 'John Doe',
      checkInDate: '2023-12-15',
      checkOutDate: '2023-12-18',
      roomType: 'Deluxe Suite',
    },
    // Add more reservations as needed
  ];

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Upcoming Hotel Reservations</Text>
      {upcomingReservations.map((reservation) => (
        <View key={reservation.id} style={styles.reservationContainer}>
          <Text>Guest Name: {reservation.guestName}</Text>
          <Text>Check-In: {reservation.checkInDate}</Text>
          <Text>Check-Out: {reservation.checkOutDate}</Text>
          <Text>Room Type: {reservation.roomType}</Text>
        </View>
      ))}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  title: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10,
  },
  reservationContainer: {
    borderWidth: 1,
    borderColor: '#ccc',
    padding: 10,
    marginBottom: 10,
  },
});

export default UpcomingReservation;