// Button.js

import React from 'react';
import { TouchableOpacity, Text } from 'react-native';

const CustomizedButton = ({ onPress, buttonStyle, textStyle, text }) => (
    <TouchableOpacity style={buttonStyle} onPress={onPress}>
        <Text style={textStyle}>{text}</Text>
    </TouchableOpacity>
);

export default CustomizedButton;
