import React, {useEffect, useState} from 'react';
import {View, Text, StyleSheet} from 'react-native';
import {AutocompleteDropdown} from "react-native-autocomplete-dropdown";
import {useFonts} from "expo-font";
import {Poppins_700Bold} from "@expo-google-fonts/poppins";

const labeledAutoComplete = ({title}) => {
    // const [selectedItem, setSelectedItem] = useState(null);
    // const [locations , setLocations] = useState([]);
    //
    // const [fontsLoaded] = useFonts({
    //     Poppins_700Bold
    // })
    // const getLocations = () => {
    //     // todo: call api to get all locations available
    //     setLocations([
    //         'London' , 'Cairo' , 'Paris'
    //     ]);
    // }
    //
    // useEffect(() => {
    //     const fetchData = async () => {
    //         await getLocations();
    //     };
    //
    //     fetchData();
    // }, []);
    //
    // return (
    //     <View style={styles.container}>
    //         <Text style={styles.label}> {title} </Text>
    //         <View style={styles.dropDownList}>
    //             <AutocompleteDropdown
    //                 clearOnFocus={false}
    //                 closeOnBlur={true}
    //                 closeOnSubmit={false}
    //                 textInputProps={{placeholder: 'ex: London/UK',}}
    //                 onSelectItem={(item) => item && setSelectedItem(item.id)}
    //                 dataSet={locations.map((title, index) => ({
    //                     id: `${index + 1}`,
    //                     title,
    //                 }))}
    //             />
    //         </View>
    //     </View>
    // );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        width: '80%',
        paddingLeft: '5%',
        paddingTop: '5%',
        height: '40%',
        backgroundColor: '#D3D6E7',
        borderRadius: 15
    },
    label: {
        fontWeight: '500',
        fontSize: 18,
    },
    dropDownList: {
        marginTop: '2%',
        flex: 1,
        paddingRight: '5%'
    }
});

export default labeledAutoComplete;
