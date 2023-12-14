import React, {useEffect, useState} from 'react';
import {View, Text, StyleSheet, FlatList, Modal, Button} from 'react-native';
import SearchAndFilterHeader from "../Components/SearchAndFilterHeader";
import HotelCard from "../Components/HotelCard";
import SortAndFilterSelector from "../Components/SortAndFilterSelector";

const SearchAndFilter = ({route, navigation}) => {
    const renderItem = ({item}) => <HotelCard hotel={item} onPress={() => onHotelPress(item)}/>;

    const [isSortModalVisible, setSortModalVisible] = useState(false);
    const [isFilterModalVisible, setFilterSortModalVisible] = useState(false);

    const [hotels, setHotels] = useState([]);

    const onHotelPress = (item) => {
        navigation.navigate('Hotel' , {
            searchDTO,
            item
        })
    }

    const {
        selectedLocation,
        setSelectedLocation,
        locations,
        setLocations,
        range,
        setRange,
        roomCount,
        setRoomCount,
        travellersCount,
        setTravellersCount,
        loading,
        setLoading,
        searchDTO,
        listOfHotels
    } = route.params;

    useEffect(() => {
        setHotels(route.params.listOfHotels["hotels"]);
    }, []);

    return (
        <View style={styles.container}>
            <SearchAndFilterHeader
                selectedLocation={selectedLocation}
                setSelectedLocation={setSelectedLocation}
                locations={locations}
                setLocations={setLocations}
                range={range}
                setRange={setRange}
                roomCount={roomCount}
                setRoomCount={setRoomCount}
                travellersCount={travellersCount}
                setTravellersCount={setTravellersCount}
                hotels={hotels}
                setHotels={setHotels}
            />
            <FlatList
                data={hotels}
                renderItem={renderItem}
                keyExtractor={(item, index) => index.toString()}
                ListFooterComponent={<View style={{height: 100}}/>}
            />
            <SortAndFilterSelector
                setHotels={setHotels}
                hotels={hotels}
                selectedLocation={selectedLocation}
                locations={locations}
                range={range}
                roomCount={roomCount}
                travellersCount={travellersCount}
                loading={loading}
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
