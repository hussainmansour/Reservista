import React from 'react';
import { View, Image, FlatList, StyleSheet } from 'react-native';

const HotelImages = () => {
    const images = [
        require('../../../assets/hotel1.jpg'),
        require('../../../assets/hotel2.jpg')
        // Add more images here if needed
    ];

    const renderItem = ({ item }) => (
        <Image style={styles.image} source={item} />
    );

    return (
        <FlatList
            horizontal
            data={images}
            keyExtractor={(item, index) => index.toString()}
            renderItem={renderItem}
            showsHorizontalScrollIndicator={false}
            contentContainerStyle={styles.imageContainer}
        />
    );
};

const styles = StyleSheet.create({
    imageContainer: {
        flexDirection: 'row',
        marginVertical: 10,
        marginBottom : '-10%'

    },
    image: {
        width: 200, // Adjust the width according to your preference
        height: 200, // Adjust the height according to your preference
        borderRadius: 10,
        marginRight: 10, // Adjust the spacing between images
    },
});

export default HotelImages;
