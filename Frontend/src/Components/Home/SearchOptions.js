import React, {useContext, useEffect, useState} from 'react';
import {View, Text, StyleSheet, Button, TouchableOpacity, ActivityIndicator, Alert} from 'react-native';
import {AutocompleteDropdown} from "react-native-autocomplete-dropdown";
import {RangeDatepicker} from '@ui-kitten/components';
import Counter from "./Counter";
import {SearchCriteriaContext} from "../../Store/searchCriteriaContext";
import {SearchOptionsContext} from "../../Store/SearchOptionsContext";
import {SearchAndFilterAPI} from "../../Utilities/New/APIs/SearchAndFilterAPI";
import {ConfigAPI} from "../../Utilities/New/APIs/ConfigAPI";
import Color from "../../Styles/Color";

const SearchOptions = ({navigation}) => {

    const {updateSearchCriteria, ...searchCriteria} = useContext(SearchCriteriaContext);
    const {updateSearchOptions, ...searchOptions} = useContext(SearchOptionsContext);

    const [loading, setLoading] = useState(false);

    const getLocations = async () => {
        // if (locations !== undefined && locations.length > 0)
        //     return

        // const locations = await ConfigAPI.getValidLocations((response) => {
            updateSearchOptions({
                locations: ['London/UK', 'Cairo/Egypt', 'Paris/France', 'Xiamen/China']
            });
        // });

        // if (locations !== undefined) {
        //     const updatedLocations = locations.map(location => `${location.city}/${location.country}`);
        //     updateSearchOptions({ locations: updatedLocations });
        // }
        //
        // console.log(searchOptions.locations);
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

    const [searchTriggered, setSearchTriggered] = useState(false);


    const today = new Date();
    const nextYear = new Date(today.getFullYear() + 5, today.getMonth(), today.getDate());

    const search = async () => {
        updateSearchCriteria(getSearchCriteriaDTO());
        setSearchTriggered(true);
    };

    useEffect(() => {

        if (searchTriggered) {
            const fetchData = async () => {
                let listOfHotels = await
                    SearchAndFilterAPI.filterAndSortHotels(searchCriteria, (response) => {

                        // in case of an expected error this should be the errorDTO
                        const responseBody = response.data;


                        if (responseBody.data !== undefined) {
                            // check for error code
                            if (responseBody.errorCode === 100) {
                                // todo : alert
                                console.log(responseBody)
                            }

                        } else {
                            // if it doesn't have the data attribute then it's not the errorDTO
                            // so, it's an unhandled exception
                            console.log(responseBody)
                        }

                    }, setLoading);
                navigation.navigate('SearchAndFilter', {listOfHotels});
            };

            fetchData().then();
            setSearchTriggered(false);
        }
    }, [searchCriteria, searchTriggered]);

    useEffect(() => {
        setLoading(true);
        const fetchData = async () => {
            await getLocations();
        };
        setLoading(false);
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
                    inputContainerStyle={{backgroundColor: "white", borderColor: Color.SEABLUE}}
                />
            </View>


            <Text style={styles.label}> Dates </Text>

            <View style={styles.date}>


                <RangeDatepicker
                    placeholder="DD/MM/YYYY"
                    range={searchOptions.checkInOutTimes}
                    onSelect={nextRange => updateSearchOptions({checkInOutTimes: nextRange})}
                    controlStyle={{ borderColor: Color.SEABLUE}}
                    // renderDay={{backgoundcolor:Color.ORANGE}}
                    min={today}
                    max={nextYear}
                />
            </View>


            <View style={styles.counters}>
                <View>
                    <Text style={styles.label}> Rooms </Text>
                    <Counter count={searchOptions.roomCount}
                             setCount={(count) => updateSearchOptions({roomCount: count})}/>
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

            {loading && <ActivityIndicator size="large" color={Color.MIDNIGHTBLUE}/>}
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
        backgroundColor: Color.PALEBLUE,
        borderRadius: 15,
        position: 'absolute',
        bottom: '25%',
        left: '10%'
    },
    label: {
        fontWeight: '500',
        fontSize: 18,
        color:Color.MIDNIGHTBLUE
    },
    dropDownList: {
        marginVertical: '2%',
        paddingRight: '5%',
    },
    button: {
        backgroundColor: Color.ORANGE,
        borderRadius: 5,
        marginRight: '5%',
        height: '12%',
        width: '50%',
        alignItems: 'center',
        alignSelf: 'center',
        justifyContent: 'center',
    },
    buttonText: {
        color: '#ffffff',
        fontSize: 25,
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
        marginBottom: '7%',
    }
});

export default SearchOptions;
