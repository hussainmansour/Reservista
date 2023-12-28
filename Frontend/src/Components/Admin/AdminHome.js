import {Alert, Dimensions, StyleSheet, Text, View} from 'react-native'
import React, {useContext, useEffect, useState} from 'react'
import Color from "../../Styles/Color";
import CustomizedButton from "../General/Buttons/CustomizedButton";
import AddVoucherModel from "./AddVoucherModel";
import AddAdminModel from "./AddAdminModel";
import ProfileAPI from "../../Utilities/ProfileAPI";
import LoadingComponent from "../General/LoadingComponent";



const AdminHome = ({navigation}) => {
    const [isAddingVoucher, setIsAddingVoucher] = useState(false);
    const [isAddingAdmin, setIsAddingAdmin] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const handleCancelAddingVoucher = () => {setIsAddingVoucher(false);};
    const handleCancelAddingAdmin = () => {setIsAddingAdmin(false);};
    const handleSaveVoucher = async (values) => {
        console.log(values)
        // try {
        //
        //     console.log("in save");
        //
        //     setIsAddingVoucher(false);
        //
        //     setIsLoading(true);
        //
        //     console.log(values);
        //
        //     const response = await ProfileAPI.updateProfile(authCtx.token,values);
        //
        //     console.log("response", response);
        //
        //     const updatedUser = { 'userName': user.userName, 'email': user.email, ...values };
        //     setUser(updatedUser);
        //
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
    const handleSaveAdmin = async (values) => {
        try {

            console.log("in save");

            setIsAddingAdmin(false);

            setIsLoading(true);

            console.log(values);

            const response = await ProfileAPI.updateProfile(authCtx.token,values);

            console.log("response", response);

            const updatedUser = { 'userName': user.userName, 'email': user.email, ...values };
            setUser(updatedUser);

            console.log("successful update");
            Alert.alert('Success', 'Profile updated successfully');
        } catch (error) {
            console.log("Error updating profile:", error);
            Alert.alert('Error', 'Failed to update profile');
        }
        finally {
            setIsLoading(false);
        }


    };
    // Wait until data is loaded before rendering
    if (isLoading) {
        return (
            <LoadingComponent></LoadingComponent>
        );
    }
    return (
        <View style={styles.container}>
            <AddVoucherModel
                isVisible={isAddingVoucher}
                onSave={handleSaveVoucher}
                onCancel={handleCancelAddingVoucher}

            ></AddVoucherModel>
            <AddAdminModel
                isVisible={isAddingAdmin}
                onSave={handleSaveAdmin}
                onCancel={handleCancelAddingAdmin}

            ></AddAdminModel>
            <CustomizedButton buttonStyle={styles.editButton1} text={"Add voucher"} onPress={()=>{setIsAddingVoucher(true)}} textStyle={styles.editButtonText}></CustomizedButton>
            <CustomizedButton buttonStyle={styles.editButton} text={"Add admin"} onPress={()=>{setIsAddingAdmin(true)}} textStyle={styles.editButtonText}></CustomizedButton>

        </View>
    );
}




const screenHeight = Dimensions.get('window').height;

const styles = StyleSheet.create({
    container:{
        flex:1,
        justifyContent: "center",
        alignItems: 'center',
        backgroundColor: Color.PALEBLUE,
    },
    editButtonText: {
        color: 'white',
        fontSize: 16,
        fontWeight: 'bold',
        textAlign: 'center',
    },
    editButton: {
        backgroundColor: Color.ORANGE, // Blue color
        paddingVertical: 10,
        paddingHorizontal: 20,
        borderRadius: 5,
        width: '50%',
        height: 100,

    },
    editButton1: {
        backgroundColor: Color.SEABLUE, // Blue color
        paddingVertical: 10,
        paddingHorizontal: 20,
        borderRadius: 5,
        width: '50%',
        height: 100,

    },

})
export default AdminHome