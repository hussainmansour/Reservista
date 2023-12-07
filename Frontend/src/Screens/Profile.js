import React, { useState } from 'react';
import { View, Text, StyleSheet, Image, Alert } from 'react-native';
import EditButton from '../Components/EditButton';

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

    return (
        <View style={styles.container}>
            <Image style={styles.profilephoto} source={require('./assets/profile.jpg')} />

            <View style={styles.fieldContainer}>
                <Text style={styles.fieldLabel}>Username</Text>
                <Text style={styles.fieldValue}>{editedUser.username}</Text>
            </View>
            <View style={styles.fieldContainer}>
                <Text style={styles.fieldLabel}>First Name</Text>
                <Text style={styles.fieldValue}>{editedUser.firstName}</Text>
            </View>

            <View style={styles.fieldContainer}>
                <Text style={styles.fieldLabel}>Last Name</Text>
                <Text style={styles.fieldValue}>{editedUser.lastName}</Text>
            </View>

            <View style={styles.fieldContainer}>
                <Text style={styles.fieldLabel}>Email</Text>
                <Text style={styles.fieldValue}>{editedUser.email}</Text>
            </View>

            <View style={styles.fieldContainer}>
                <Text style={styles.fieldLabel}>Phone</Text>
                <Text style={styles.fieldValue}>{editedUser.phone}</Text>
            </View>

            <View style={styles.fieldContainer}>
                <Text style={styles.fieldLabel}>Gender</Text>
                <Text style={styles.fieldValue}>{editedUser.gender}</Text>
            </View>

            <View style={styles.fieldContainer}>
                <Text style={styles.fieldLabel}>Nationality</Text>
                <Text style={styles.fieldValue}>{editedUser.nationality}</Text>
            </View>

            <View style={styles.fieldContainer}>
                <Text style={styles.fieldLabel}>Passport Number</Text>
                <Text style={styles.fieldValue}>{editedUser.passportNumber}</Text>
            </View>

            <EditButton onPress={handleEdit} />
        </View>
    );
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

export default UserProfile;
