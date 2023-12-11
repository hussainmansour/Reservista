// DropdownList.js

import React from 'react';
import { View, Text, Picker } from 'react-native';
import styles from '../Styles/Editstyles';

const DropdownList = ({ label, selectedValue, onValueChange, onBlur, items }) => (
    <View style={styles.fieldContainer}>
        <Text style={styles.fieldLabel}>{label}:</Text>
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
    </View>
);

export default DropdownList;