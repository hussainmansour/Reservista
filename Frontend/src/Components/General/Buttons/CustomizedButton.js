// Button.js

import React from 'react';
import { TouchableOpacity, Text } from 'react-native';

const CustomizedButton = ({ onPress, buttonStyle, textStyle, text }) => {

    const tStyle={...styles.Text,...textStyle};

    const bStyle={...styles.ButtonContainer,...buttonStyle}
    
    return (
        <TouchableOpacity style={bStyle} onPress={onPress}>
            <Text style={tStyle}>{text}</Text>
        </TouchableOpacity>
    )
};

export default CustomizedButton;

const styles = StyleSheet.create({
    ButtonContainer:{
        
        marginTop: 30,
        width: 118,
        height: 43,
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: 10,
        backgroundColor: '#728FF3',
        alignSelf: 'center',
    },
    Text:{
        fontSize: 20,
        color: 'white'
    },
})
