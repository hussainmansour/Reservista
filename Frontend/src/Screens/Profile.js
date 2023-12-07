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
            <Image style={styles.profilephoto} source={require('./assets/profile.jpg')} />

            {renderField('Username', editedUser.username)}
            {renderField('First Name', editedUser.firstName)}
            {renderField('Last Name', editedUser.lastName)}
            {renderField('Email', editedUser.email)}
            {renderField('Phone', editedUser.phone)}
            {renderField('Gender', editedUser.gender)}
            {renderField('Nationality', editedUser.nationality)}
            {renderField('Passport Number', editedUser.passportNumber)}

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
    profilephoto: {
        width: 150,
        height: 150,
        borderRadius: 75,
        marginBottom: 20,
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