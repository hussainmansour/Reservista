import React, {useContext, useEffect, useState} from 'react';
import {View, Text, StyleSheet, FlatList} from 'react-native';
import SearchAndFilterHeader from './SearchAndFilterHeader';
import HotelCard from "./HotelCard";
import SortAndFilterSelector from "./SortAndFilterSelector";

const SearchAndFilter = ({route, navigation}) => {

    const [hotels, setHotels] = useState([]);
    const renderItem = ({item}) => <HotelCard hotel={item} onPress={() => onHotelPress(item)}/>;
    const onHotelPress = (item) => navigation.navigate('Hotel', {item});

    useEffect(() => {
        setHotels(route.params.listOfHotels["hotels"]);
    }, []);

    // todo: fix loading
    const [loading,setLoading] = useState(false);

    return (
        <View style={styles.container}>
            <SearchAndFilterHeader/>
            <FlatList
                data={hotels}
                renderItem={renderItem}
                keyExtractor={(item, index) => index.toString()}
                ListFooterComponent={<View style={{height: 100}}/>}
            />
            <SortAndFilterSelector
                setHotels={setHotels}
                setLoading={setLoading}
            />
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'flex-start',
        backgroundColor: 'white'
    },
    modalContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
    },
});

export default SearchAndFilter;
