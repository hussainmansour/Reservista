import React, {useContext, useEffect, useState} from 'react';
import {View, Text, StyleSheet, Button, TouchableOpacity, ActivityIndicator} from 'react-native';
import {AutocompleteDropdown} from "react-native-autocomplete-dropdown";
import {useFonts} from 'expo-font';
import {Poppins_700Bold} from "@expo-google-fonts/poppins"
import {RangeDatepicker} from '@ui-kitten/components';
import Counter from "./Counter";
import {searchForHotels} from "../../Utilities/API";
import {SearchCriteriaContext} from "../../Store/searchCriteriaContext";
import {SearchOptionsContext} from "../../Store/SearchOptionsContext";


const SearchOptions = ({navigation}) => {

    const {updateSearchCriteria,...searchCriteria} = useContext(SearchCriteriaContext);
    const {updateSearchOptions , ...searchOptions} = useContext(SearchOptionsContext);

    const [loading, setLoading] = useState(false);

    // const [fontsLoaded] = useFonts({
    //     Poppins_700Bold
    // })

    const getLocations = async () => {
        // todo: call api to get all locations available
        updateSearchOptions({
            locations: ['London/UK', 'Cairo/Egypt', 'Paris/France' , 'Xiamen/China']
        });
    }

    const getSearchCriteriaDTO = () => {
        const {
            selectedLocation,
            locations,
            checkInOutTimes,
            roomCount,
            travellersCount,
        } = searchOptions;

        return {
            city: locations[selectedLocation - 1]?.split("/")[0],
            country: locations[selectedLocation - 1]?.split("/")[1],
            numberOfRooms: roomCount,
            numberOfTravelers: travellersCount,
            pageNumber: 0,
            pageSize: 20,
            checkIn: checkInOutTimes["startDate"],
            checkOut: checkInOutTimes["endDate"]
        };
    }

    const search = async () => {
        updateSearchCriteria(getSearchCriteriaDTO());
        let listOfHotels = await searchForHotels(searchCriteria, setLoading)
        navigation.navigate('SearchAndFilter', {listOfHotels});
    }

    useEffect(() => {
        const fetchData = async () => {
            await getLocations();
        };

        fetchData().catch(console.error);
    }, []);

    return (
        <View style={styles.container}>
            <Text style={styles.label}> Location </Text>
            <View style={styles.dropDownList}>
                <AutocompleteDropdown
                    clearOnFocus={false}
                    closeOnBlur={true}
                    closeOnSubmit={false}
                    textInputProps={{placeholder: 'ex: London/UK'}}
                    onSelectItem={(item) => item &&
                        updateSearchOptions({selectedLocation: item.id})}
                    dataSet={searchOptions.locations.map((title, index) => ({
                        id: `${index + 1}`,
                        title,
                    }))}
                />
            </View>


            <Text style={styles.label}> Dates </Text>

            <View style={styles.date}>
                <RangeDatepicker
                    range={searchOptions.checkInOutTimes}
                    onSelect={nextRange => updateSearchOptions({checkInOutTimes: nextRange})}
                />
            </View>


            <View style={styles.counters}>
                <View>
                    <Text style={styles.label}> Rooms </Text>
                    <Counter count={searchOptions.roomCount} setCount={(count) => updateSearchOptions({roomCount: count})}/>
                </View>

                <View>
                    <Text style={styles.label}> Travellers </Text>
                    <Counter count={searchOptions.travellersCount}
                             setCount={(count) => updateSearchOptions({travellersCount: count})}/>
                </View>
            </View>

            <TouchableOpacity style={styles.button} onPress={search}>
                <Text style={styles.buttonText}>{"Search"}</Text>
            </TouchableOpacity>

            {loading && <ActivityIndicator size="large" color="#0000ff"/>}
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
        // fontFamily: 'Poppins_700Bold'
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
        // fontFamily: 'Poppins_700Bold'
    },
    date: {
        paddingRight: '5%',
        marginVertical: '2%',
    },
    counters: {
        flexDirection: 'row',
        justifyContent: 'space-evenly',
        paddingRight: '5%',
        marginTop: '5%',
        marginBottom: '7%'
    }
});

export default SearchOptions;
