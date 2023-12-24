// Button.js

import React from 'react';
import { TouchableOpacity, Text } from 'react-native';

const CustomizedButton = ({ onPress, buttonStyle, textStyle, text }) => {
    
    return (
        <TouchableOpacity style={buttonStyle} onPress={()=>{
            onPress();
            console.log("pressed");
        }}>
            <Text style={textStyle}>{text}</Text>
        </TouchableOpacity>
    )
};

export default CustomizedButton;
