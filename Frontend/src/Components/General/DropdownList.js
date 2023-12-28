// DropdownList.js

import React from 'react';
import {Picker} from '@react-native-picker/picker';
import { View, Text } from 'react-native';
import styles from '../../Styles/Editstyles';



const DropdownList = ({ label, selectedValue, onValueChange, onBlur, items, color, errorMessage }) => {
    // console.log(`Label: ${label}`);
    // console.log(`Selected Value: ${selectedValue}`);
    // console.log('Items:', items);

    const labelStyle = {
        ...styles.fieldLabel,
        color: color || styles.fieldLabel.color
    };

    return (
        <View style={styles.fieldContainer}>
            <Text style={labelStyle}>{label}</Text>
            <View style={styles.input}>
                <Picker
                    selectedValue={selectedValue}
                    dropdownIconColor={'gray'}
                    onValueChange={(itemValue) => onValueChange(itemValue)}
                    onBlur={() => onBlur()}
                >
                    {items.map((item, index) => (
                        <Picker.Item key={index} label={item.label} value={item.value} />
                    ))}
                </Picker>
            </View>
            {errorMessage ? (
                <Text style={styles.errorText}>{errorMessage}</Text>
            ) : null}
        </View>
    )
};

export default DropdownList;