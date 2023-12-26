// Button.js

import React from 'react';
import { TouchableOpacity, Text, StyleSheet } from 'react-native';

const CustomizedButton = ({ onPress, buttonStyle, textStyle, text, disabled }) => {
    const tStyle = { ...styles.Text, ...textStyle };
    const bStyle = {
        ...styles.ButtonContainer,
        ...buttonStyle,
        backgroundColor: disabled ? '#A0A0A0' : buttonStyle.backgroundColor,
    };

    return (
        <TouchableOpacity
            style={bStyle}
            onPress={disabled ? null : onPress} // Disable onPress when disabled
            activeOpacity={disabled ? 1 : 0.7}  // Adjust opacity when disabled
        >
            <Text style={tStyle}>{text}</Text>
        </TouchableOpacity>
    );
};

export default CustomizedButton;

const styles = StyleSheet.create({
    ButtonContainer: {
        marginBottom: 60,
        marginTop: 30,
        width: 118,
        height: 43,
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: 10,
        backgroundColor: '#728FF3',
        alignSelf: 'center',
    },
    Text: {
        fontSize: 20,
        color: 'white',
    },
});
