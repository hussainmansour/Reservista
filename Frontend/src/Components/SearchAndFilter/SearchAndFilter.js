import React, {useContext, useEffect, useState} from 'react';
import {View, Text, StyleSheet, FlatList} from 'react-native';
import SearchAndFilterHeader from './SearchAndFilterHeader';
import HotelCard from "./HotelCard";
import SortAndFilterSelector from "./SortAndFilterSelector";
import {SearchCriteriaContext} from "../../Store/searchCriteriaContext";
import {SearchAndFilterAPI} from "../../Utilities/New/APIs/SearchAndFilterAPI";

const SearchAndFilter = ({route, navigation}) => {

    const [hotels, setHotels] = useState([]);

    const {updateSearchCriteria, ...searchCriteria} =
        useContext(SearchCriteriaContext);

    const {
        checkIn,
        checkOut,
        numberOfTravelers,
        numberOfRooms
    } = searchCriteria;

    const onHotelPress = (item) => navigation.navigate('Hotel', {item});
    const renderItem = ({item}) =>
        <HotelCard hotel={item} onPress={async () => {
            const HotelDTO = {
                hotelId: item.id,
                numberOfRooms: numberOfRooms,
                numberOfTravelers: numberOfTravelers,
                checkIn: checkIn,
                checkOut: checkOut
            }

            const response = await SearchAndFilterAPI.getHotel(
                HotelDTO,
                (response) => {
                    const responseBody = response.data;

                    if (responseBody.data !== undefined) {

                        if (responseBody.errorCode === 50)
                            Alert('Error' , responseBody.data);

                    } else console.log(responseBody)
                }
            );
            navigation.navigate('Hotel', {response})
        }}/>;


    useEffect(() => {
        setHotels(route.params.listOfHotels["hotels"]);
    }, [route]);

    // todo: fix loading
    const [loading, setLoading] = useState(false);

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
