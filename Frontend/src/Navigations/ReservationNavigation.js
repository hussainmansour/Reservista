import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HotelView from '../Screens/HotelView';
import Reservation from './Resrvation';
import CartScreen from '../Screens/CartScreen';

const Stack = createStackNavigator();

const ReservationNavigation = () => {

  const Dummy={
    price: 100,
    title: "title",
    count: 4,
    roomDescriptionId: "roomId",
    hotelID:"hotelId",
    refundable:true,
    fullyRefundableRate: 15,
    checkIn: "2024-03-01T00:00:00Z",
    checkOut: "2024-03-02T00:00:00Z",
    foodOptions : { breakfastPrice: 21, lunchPrice: 57, dinnerPrice: 35 },
    hotelName: "hotelName",
    hotelAddress: "hotelAddress",
    city: "hotelCity",
    country: "hotelCountry",
    hotelDescription: "hotelDescription",
    hotelEmail: "hotelEmail",
    hotelPhone: "hotelPhone",
    rating:5,
    starRating:4,
    reviewCount:120
  }

  return (
      <Stack.Navigator initialRouteName="HotelView">
        <Stack.Screen
          name="HotelView"
          component={HotelView}
          initialParams={{Dummy}}
          options={{
            title: 'Hotel',
            headerStyle: {
              backgroundColor: '#75C2F6', // Set your header background color
            },
            headerTintColor: '#fff', // Set your header text color
            headerTitleStyle: {
              fontWeight: 'bold', // Set your header title text style
            },
          }}
        />
        <Stack.Screen
          name="CartScreen"
          component={CartScreen}
          options={{
            title: 'Reservation',
            headerStyle: {
              backgroundColor: '#75C2F6',
            },
            headerTintColor: '#fff',
            headerTitleStyle: {
              fontWeight: 'bold',
            },
          }}
        />
      </Stack.Navigator>
  );
};

export default ReservationNavigation;
