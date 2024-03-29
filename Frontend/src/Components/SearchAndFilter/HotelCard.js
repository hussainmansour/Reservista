import React, {useEffect, useState} from 'react';
import {View, Text, Image, TouchableOpacity, FlatList, StyleSheet} from 'react-native';
import {getBaseURL} from "../../Utilities/New/BaseURL";

const HotelCard = ({hotel, onPress}) => {

    return (
        <TouchableOpacity style={styles.card} onPress={onPress}>
            <View style={styles.imageContainer}>
                <Image source={
                    {
                        uri: hotel["imagesUrls"][0].replace("http://localhost:8080", getBaseURL)
                    }} style={styles.image}/>
            </View>
            <View style={styles.textContainer}>
                <Text style={styles.hotelTitle}>{hotel.name}</Text>
                <Text>{"Location: " + hotel.city + '/' + hotel.country}</Text>
                <Text>{`Rating: ${hotel.rating}`}</Text>
                <Text>{`ReviewCount: ${hotel.reviewCount}`}</Text>
                <Text>{`Stars: ${hotel.starRating}`}</Text>
                <Text>{`Price: ${hotel.minRoomPrice} $`}</Text>
            </View>
        </TouchableOpacity>
    );
};

const styles = StyleSheet.create({
    container: {
        padding: 10,
    },
    card: {
        flexDirection: 'row',
        padding: 16,
        borderBottomWidth: 1,
        borderBottomColor: '#ccc',
    },
    imageContainer: {
        marginRight: 16,
    },
    image: {
        flex: 1,
        width: 100,
        height: 100,
        resizeMode: 'cover',
        borderRadius: 8,
    },
    textContainer: {
        flex: 1,
    },
    hotelTitle: {
        fontSize: 20,
        fontWeight: 'bold',
        marginBottom: 5,
        color: '#333333',
    },
    rating: {
        color: 'gray',
    },
    stars: {
        color: 'gray',
    },
    price: {
        color: 'green',
        fontWeight: 'bold',
    },
    location: {
        color: 'gray',
    },
    button: {
        backgroundColor: '#3498db',
        padding: 10,
        alignItems: 'center',
        borderBottomLeftRadius: 10,
        borderBottomRightRadius: 10,
    },
    buttonText: {
        color: 'white',
        fontWeight: 'bold',
    },

});

export default HotelCard;
