import React, { useState, useContext, useEffect } from 'react';
import { View, Text, StyleSheet, Image, Alert } from 'react-native';
import CustomizedButton from '../General/Buttons/CustomizedButton';
import ProfileEditScreen from './ProfileEditScreen';
import { AuthContext } from '../../Store/authContext';

import LoadingComponent from '../General/LoadingComponent';
import { ProfileAPI } from '../../Utilities/New/APIs/ProfileAPI';
import Color from '../../Styles/Color';


const Profile = () => {
    const [isEditing, setIsEditing] = useState(false);
    const [user, setUser] = useState({});
    const [isLoading, setIsLoading] = useState(true);

    // Getting the profile
    useEffect(() => {
        const fetchData = async () => {
            let response= await ProfileAPI.viewProfile((response)=>{
                const responseBody=response.data;
                if(responseBody.data!==undefined){
                    console.log(responseBody.data);
                    Alert.alert('Error',responseBody.data);
                }
                else{
                    console.log('====================================');
                    console.log(responseBody);
                    console.log('====================================');
                    Alert.alert("Error", "Failed to get your profile");
                }
            },setIsLoading);

            if(response!==undefined){
                console.log('====================================');
                console.log(response);
                console.log('====================================');
                setUser(response);
            }
        };

        fetchData();
    }, []);

    const handleEdit = () => {
        setIsEditing(true);
    };

    const handleCancel = () => {
        setUser({ ...user });
        setIsEditing(false);
    };

    // updating the profile
    const handleSave = async (values) => {
        setIsEditing(false);
        console.log("in save");
        let response = await ProfileAPI.editProfile(values,()=>{
            const responseBody=response.data;
                if(responseBody.data!==undefined){
                    console.log(responseBody.data);
                    Alert.alert('Error',responseBody.data);
                }
                else{
                    console.log('====================================');
                    console.log(responseBody);
                    console.log('====================================');
                    Alert.alert("Error", "Failed to update your profile");
                }
            },setIsLoading);
        if(response!==undefined){
            console.log('====================================');
            console.log(response);
            console.log('====================================');

            const updatedUser = { 'userName': user.userName, 'email': user.email, ...values };
            setUser(updatedUser);

            console.log("successful update");
            Alert.alert('Success', 'Profile updated successfully');
        }


        // try {

        //     console.log("in save");

        //     setIsEditing(false);

        //     setIsLoading(true);

        //     console.log(values);

        //     const response = await ProfileAPI.updateProfile(authCtx.token,values);

        //     console.log("response", response);

        //     const updatedUser = { 'userName': user.userName, 'email': user.email, ...values };
        //     setUser(updatedUser);

        //     console.log("successful update");
        //     Alert.alert('Success', 'Profile updated successfully');
        // } catch (error) {
        //     console.log("Error updating profile:", error);
        //     Alert.alert('Error', 'Failed to update profile');
        // }
        // finally {
        //     setIsLoading(false);
        // }
    };

    // Wait until data is loaded before rendering
    if (isLoading) {
        return (
            <LoadingComponent></LoadingComponent>
        );
    }



    const renderProfile = () => (
        <View style={styles.container}>

            {renderField('User Name', user.userName)}
            {renderField('Email', user.email)}
            {renderField('First Name', user.firstName)}
            {renderField('Middle Name', user.middleName)}
            {renderField('Last Name', user.lastName)}
            {renderField('Gender', user.gender)}
            {renderField('Nationality', user.nationality)}
            {renderField('Date of Birth', user.birthDate)}

            <ProfileEditScreen
                isVisible={isEditing}
                onSave={handleSave}
                onCancel={handleCancel}
                user={user}

            ></ProfileEditScreen>

            <CustomizedButton text={"Edit"} onPress={handleEdit} textStyle={styles.editButtonText} buttonStyle={styles.editButton} ></CustomizedButton>
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
        paddingBottom: 40
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
    editButton: {
        backgroundColor: Color.MIDNIGHTBLUE, // Blue color
        paddingVertical: 10,
        paddingHorizontal: 20,
        borderRadius: 5,
        width: '50%',
        height: 50,

    },
    editButtonText: {
        color: 'white',
        fontSize: 16,
        fontWeight: 'bold',
        textAlign: 'center',
    },
});

export default Profile;