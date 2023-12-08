import React, { useState } from 'react';
import { View, Text, StyleSheet, Image, Alert } from 'react-native';
import EditButton from '../Components/EditButton'


const Profile = ({ route }) => {
    const { user } = route.params;
    const [isEditing, setIsEditing] = useState(false);
    const [editedUser, setEditedUser] = useState({ ...user });

    const handleEdit = () => {
        setIsEditing(true);
    };

    const handleCancel = () => {
        setEditedUser({ ...editedUser });
        setIsEditing(false);
    };

    const handleSave = (values) => {
        // Validation logic and save/update API call
        // For simplicity, let's assume the validation is successful
        setIsEditing(false);
        setEditedUser(values);
        Alert.alert('Success', 'Profile updated successfully');
    };

    const renderProfile = () => (
        <View style={styles.container}>

            {renderField('First Name', editedUser.firstName)}
            {renderField('Middle Name', editedUser.middleName)}
            {renderField('Last Name', editedUser.lastName)}
            {renderField('Email', editedUser.email)}
            {renderField('Gender', editedUser.gender)}
            {renderField('Nationality', editedUser.nationality)}
            {renderField('Birthday', editedUser.birthday)}

            <EditButton onPress={handleEdit} />
        </View>
    );

    const renderField = (label, value) => (
        <View style={styles.fieldContainer}>
            <Text style={styles.fieldLabel}>{label}</Text>
            <Text style={styles.fieldValue}>{value}</Text>
        </View>
    );

    return renderProfile();
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
        padding: 16,
        margin: 5,
        backgroundColor: '#fff',
        paddingBottom:40
    },
    fieldContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        marginBottom: 20,
    },
    fieldLabel: {
        width: 120,
        fontSize: 16,
        fontWeight: 'bold',
        color: '#333',
    },
    fieldValue: {
        flex: 1,
        fontSize: 16,
        color: '#555',
    },
});

export default Profile;