import React from 'react';
import { View, Image, FlatList, StyleSheet } from 'react-native';
import { getBaseURL } from '../../Utilities/New/BaseURL';

const HotelImages = ({ images }) => {
    console.log('====================================');
    console.log(images[0]);
    console.log('====================================');
    console.log('====================================');
    console.log("hotelimages");
    console.log('====================================');
    console.log(images)
    const renderItem = ({ item }) => (
        <Image style={styles.image} source={{ uri: images[0].replace("http://localhost:8080/", getBaseURL) }} />
    );

    return (
        // <FlatList
        //     horizontal
        //     data={images}
        //     keyExtractor={(item, index) => index.toString()}
        //     renderItem={renderItem}
        //     showsHorizontalScrollIndicator={false}
        //     contentContainerStyle={styles.imageContainer}
        // />
        <View style={styles.imageContainer}>
            <Image source={
                {
                    uri: images[0].replace("http://localhost:8080/", getBaseURL)
                }} style={styles.image} />
        </View>
    );
};

const styles = StyleSheet.create({
    imageContainer: {
        flexDirection: 'row',
        marginVertical: 10,
        marginBottom: '-10%'

    },
    image: {
        width: 200, // Adjust the width according to your preference
        height: 200, // Adjust the height according to your preference
        borderRadius: 10,
        marginRight: 10, // Adjust the spacing between images
    },
});

export default HotelImages;
