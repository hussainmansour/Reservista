// Button.js

import React from 'react';
import { TouchableOpacity, Text } from 'react-native';

const CustomizedButton = ({ onPress, style, text }) => (
    <TouchableOpacity style={style} onPress={onPress}>
        <Text style={styles.buttonText}>{text}</Text>
    </TouchableOpacity>
);

export default CustomizedButton;
