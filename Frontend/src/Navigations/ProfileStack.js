import 'react-native-gesture-handler';
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import UpcomingReservation from '../Screens/UpcomingReservation';
import ReservationHistory from '../Screens/ResrvationHistory';
import { View, TouchableOpacity, Text, StyleSheet } from 'react-native';
import Profile from '../Screens/Profile';

const Stack = createStackNavigator();

const ProfileStack = () => {
  // const user = {
  //   // dummy user
  //   username: 'huussein',
  //   firstName: 'Hussein',
  //   middleName: 'Khaled',
  //   lastName: 'Khadrawy',
  //   email: 'husseinkhaled733@gmail.com',
  //   gender: 'Male',
  //   nationality: 'EG',
  //   birthdate: '2002-09-09'
  // };

  const headerOptions = ({ navigation }) => ({
    headerStyle: {
      backgroundColor: '#728FF3',
    },
    headerTintColor: '#fff',
    headerTitleStyle: {
      fontWeight: 'bold',
      fontSize: 20,
    },
    headerRight: () => (
      <View style={styles.headerButtonsContainer}>
        <TouchableOpacity
          style={styles.headerButton}
          onPress={() => navigation.navigate('UpcomingReservation')}
        >
          <Text style={styles.headerButtonText}>Upcoming</Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.headerButton}
          onPress={() => navigation.navigate('ReservationHistory')}
        >
          <Text style={styles.headerButtonText}>History</Text>
        </TouchableOpacity>
      </View>
    ),
  });

  return (
      <Stack.Navigator initialRouteName="UserProfile">
        <Stack.Screen
          name="UserProfile"
          component={Profile}
          options={({ navigation }) => ({
            title: 'Profile',
            ...headerOptions({ navigation }),
          })}
        />
        <Stack.Screen
          name="UpcomingReservation"
          component={UpcomingReservation}
          options={({ navigation }) => ({
            title: 'Upcoming Reservations',
            ...headerOptions({ navigation }),
            headerRight:null,
          })}
        />
        <Stack.Screen
          name="ReservationHistory"
          component={ReservationHistory}
          options={({ navigation }) => ({
            title: 'Reservation History',
            ...headerOptions({ navigation }),
            headerRight:null,
          })}
        />
      </Stack.Navigator>
  );
};

const styles = StyleSheet.create({
  headerButtonsContainer: {
    flexDirection: 'row',
    marginRight: 10,
  },
  headerButton: {
    marginLeft: 10,
  },
  headerButtonText: {
    color: 'white',
    fontSize: 16,
  },
});

export default ProfileStack;
