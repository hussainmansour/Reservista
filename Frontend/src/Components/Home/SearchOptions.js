import React, {useEffect, useState} from 'react';
import {View, Text, StyleSheet, Button, TouchableOpacity, ActivityIndicator} from 'react-native';
import {AutocompleteDropdown} from "react-native-autocomplete-dropdown";
import {useFonts} from 'expo-font';
import {Poppins_700Bold , Poppins_300Light} from "@expo-google-fonts/poppins"
import {Layout, RangeDatepicker} from '@ui-kitten/components';
import Counter from "./Counter";
import CounterInput from "react-native-counter-input";
import {searchForHotels} from "../../Utilities/API";
import {NetworkInfo} from "react-native-network-info";
import {SearchAndFilterAPI} from "../../Utilities/New/APIs/SearchAndFilterAPI";

const SearchOptions = ({navigation}) => {
    const [selectedLocation, setSelectedLocation] = useState(null);
    const [locations, setLocations] = useState([]);
    const [range, setRange] = useState({});
    const [roomCount, setRoomCount] = useState(0);
    const [travellersCount, setTravellersCount] = useState(0);

    const [loading, setLoading] = useState(false);

    const [fontsLoaded] = useFonts({
        Poppins_700Bold
    })

    const getLocations = async () => {
        // todo: call api to get all locations available
        setLocations([
            'London/UK', 'Cairo/Egypt', 'Paris/France'
        ]);
    }

    const getSearchDTO = () => {
        return  {
            "city": locations[selectedLocation - 1].split("/")[0],
            "country": locations[selectedLocation - 1].split("/")[1],
            "numberOfRooms": roomCount,
            "numberOfTravelers": travellersCount,
            "pageNumber": 0,
            "pageSize": 20,
            "checkIn": range["startDate"],
            "checkOut": range["endDate"],
            "minPrice": 0,
            "maxPrice": 100000,
            "minStars": 0,
            "maxStars": 5,
            "minRating": 0.0,
            "maxRating": 10.0,
            "sortBy": "price",
            "sortOrder": "asc"
        }
    }

    const search = async () => {
        let listOfHotels = await SearchAndFilterAPI.filterAndSortHotels(
            getSearchDTO(),
            null,
            setLoading
        )

        console.log(listOfHotels)

        // let listOfHotels = await searchForHotels(getSearchDTO() , setLoading)
        navigation.navigate('SearchAndFilter' , {
            searchDTO : getSearchDTO(),
            listOfHotels,
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
            setLoading
        } )
    }

    useEffect(() => {
        const fetchData = async () => {
            await getLocations();
        };

        fetchData();
    }, []);

    return (
        <View style={styles.container}>
            <Text style={styles.label}> Location </Text>
            <View style={styles.dropDownList}>
                <AutocompleteDropdown
                    clearOnFocus={false}
                    closeOnBlur={true}
                    closeOnSubmit={false}
                    textInputProps={{ placeholder: 'ex: London/UK' }}
                    onSelectItem={(item) => item && setSelectedLocation(item.id)}
                    dataSet={locations.map((title, index) => ({
                        id: `${index + 1}`,
                        title,
                    }))}
                />
            </View>


            <Text style={styles.label}> CheckIn/CheckOut Date </Text>

            <View style={styles.date}>
                <RangeDatepicker
                    range={range}
                    onSelect={nextRange => setRange(nextRange)}
                />
            </View>


            <View style={styles.counters}>
                <View>
                    <Text style={styles.label}> Rooms </Text>
                    <Counter count={roomCount} setCount={setRoomCount}/>
                </View>

                <View>
                    <Text style={styles.label}> Travellers </Text>
                    <Counter count={travellersCount} setCount={setTravellersCount}/>
                </View>
            </View>

            <TouchableOpacity style={styles.button} onPress={search}>
                <Text style={styles.buttonText}>{"Search"}</Text>
            </TouchableOpacity>

            {loading && <ActivityIndicator size="large" color="#0000ff" />}
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        width: '80%',
        paddingLeft: '5%',
        paddingTop: '5%',
        height: '40%',
        backgroundColor: '#D3D6E7',
        borderRadius: 15,
        position: 'absolute',
        bottom: '30%',
        left: '10%'
    },
    label: {
        fontWeight: '500',
        fontSize: 18,
        fontFamily: 'Poppins_700Bold'
    },
    dropDownList: {
        marginVertical: '2%',
        paddingRight: '5%',
    },
    button: {
        backgroundColor: '#3498db',
        borderRadius: 5,
        marginRight: '5%',
        height: '12%',
        width: '50%',
        alignItems: 'center',
        alignSelf: 'center',
        justifyContent: 'center'
    },
    buttonText: {
        color: '#000000',
        fontSize: 25,
        fontFamily: 'Poppins_700Bold'
    },
    date: {
        paddingRight: '5%',
        marginVertical: '2%',
    },
    counters:{
        flexDirection: 'row',
        justifyContent: 'space-evenly',
        paddingRight: '5%',
        marginTop: '5%' ,
        marginBottom: '7%'
    }
});

export default SearchOptions;
