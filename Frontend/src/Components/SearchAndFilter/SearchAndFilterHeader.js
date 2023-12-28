import React, {useContext} from 'react';
import {StyleSheet, Text, TouchableOpacity, View} from "react-native";
import {useFonts} from "expo-font";
import {Poppins_700Bold} from "@expo-google-fonts/poppins";
import {AutocompleteDropdown} from "react-native-autocomplete-dropdown";
import {RangeDatepicker} from "@ui-kitten/components";
import Counter from "../Home/Counter";
import {SearchOptionsContext} from "../../Store/SearchOptionsContext";

const SearchAndFilterHeader = () => {

    const {updateSearchOptions , ...searchOptions} =
        useContext(SearchOptionsContext);

    const {
        locations,
        checkInOutTimes,
        roomCount,
        travellersCount,
    } = searchOptions;


    const search = () => {
        // call search api to get the hotels and navigate to the search and filter screen
    }

    return (
        <View style={styles.header}>
            <View style={styles.searchSelectors}>

                <View style={styles.dateSelector}>
                    <Text style={styles.label}> Dates </Text>
                    <View style={styles.date}>
                        <RangeDatepicker
                            range={checkInOutTimes}
                            onSelect={nextRange => updateSearchOptions({checkInOutTimes: nextRange})}
                        />
                    </View>
                </View>


                <View style={styles.counters}>
                    <View>
                        <Text style={styles.label}> Rooms </Text>
                        <Counter count={roomCount} setCount={(count) => updateSearchOptions({roomCount: count})}/>
                    </View>

                    <View>
                        <Text style={styles.label}> Travellers </Text>
                        <Counter count={travellersCount} setCount={(count) => updateSearchOptions({travellersCount: count})}/>
                    </View>
                </View>
            </View>
            <TouchableOpacity style={styles.button} onPress={search}>
                <Text style={styles.buttonText}>{"Search"}</Text>
            </TouchableOpacity>
        </View>

    );
};

const styles = StyleSheet.create({
    searchSelectors: {
        paddingLeft: '5%',
        paddingTop: '2%',
        backgroundColor: '#D3D6E7',
        borderRadius: 15,
        marginRight: '10%',
        marginTop: '-13%',
        marginBottom: '5%'
    },
    header: {
        paddingLeft: '10%',
        paddingTop: '25%',
        alignItems: 'center',
        justifyContent: 'flex-start',
        width: '100%',
        height: '38%',
        backgroundColor: '#4536F9',
        borderBottomLeftRadius: 40,
        borderBottomRightRadius: 40,
    },
    label: {
        fontWeight: '500',
        fontSize: 18,
    },
    dropDownList: {
        marginVertical: '2%',
        paddingRight: '5%',
        width: '60%',
        marginLeft: '9%'
    },
    button: {
        backgroundColor: '#3498db',
        borderRadius: 5,
        height: '18%',
        width: '50%',
        marginRight: '10%',
        alignItems: 'center',
        justifyContent: 'center'
    },
    buttonText: {
        color: '#000000',
        fontSize: 28,
    },
    date: {
        paddingRight: '5%',
        marginTop: '2%',
        width: '60%',
        marginLeft: '17%'
    },
    counters: {
        flexDirection: 'row',
        justifyContent: 'space-evenly',
        paddingRight: '5%',
        marginTop: '5%',
        marginBottom: '5%'
    },
    locationSelector: {
        flexDirection: 'row',
        alignItems: 'center'
    },
    dateSelector: {
        flexDirection: 'row',
        alignItems: 'center'
    }
});


export default SearchAndFilterHeader;
