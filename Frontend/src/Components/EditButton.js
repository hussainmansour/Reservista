import React from 'react';
import { TouchableOpacity, Text, StyleSheet } from 'react-native';

const EditButton = ({ onPress }) => {
    return (
        <TouchableOpacity style={styles.editButton} onPress={onPress}>
            <Text style={styles.buttonText}>Edit</Text>
        </TouchableOpacity>
    );
};
const styles = StyleSheet.create({
    editButton: {
        backgroundColor: '#728FF3', // Blue color
        paddingVertical: 10,
        paddingHorizontal: 20,
        borderRadius: 5,
        width:'50%',
        height:50,
        
    },
    buttonText: {
        color: 'white',
        fontSize: 16,
        fontWeight: 'bold',
        textAlign: 'center',
    },
});

export default EditButton;