import {Alert, Dimensions, StyleSheet, View} from 'react-native'
import React, { useEffect, useState} from 'react'
import Color from "../../Styles/Color";
import CustomizedButton from "../General/Buttons/CustomizedButton";
import AddVoucherModel from "./AddVoucherModel";
import AddAdminModel from "./AddAdminModel";
import ProfileAPI from "../../Utilities/ProfileAPI";
import LoadingComponent from "../General/LoadingComponent";
import {AdminAPI} from "../../Utilities/New/APIs/AdminAPI";



const AdminHome = ({navigation}) => {
    const [isAddingVoucher, setIsAddingVoucher] = useState(false);
    const [isAddingAdmin, setIsAddingAdmin] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const handleCancelAddingVoucher = () => {setIsAddingVoucher(false);};
    const handleCancelAddingAdmin = () => {setIsAddingAdmin(false);};
    const handleSaveVoucher = async (values) => {
        console.log(values)
        let response = await AdminAPI.saveVoucher(values, (response) => {
            // in case of an expected error this should be the errorDTO
            const responseBody = response.data;

            if (responseBody.data !== undefined) {
                Alert.alert('Error', responseBody.data);
            }
            else{
                console.log(responseBody)
            }
        }, setIsLoading);
            // success
            if (response !== undefined) {
                setIsAddingVoucher(false);
                setIsLoading(false);
                Alert.alert('Success', 'Voucher added successfully');
            }
    };
    const handleSaveAdmin = async (values) => {
        console.log(values)
        let response = await AdminAPI.saveAdmin(values, (response) => {
            // in case of an expected error this should be the errorDTO
            const responseBody = response.data;


            if (responseBody.data !== undefined) {
                Alert.alert('Error', responseBody.data);
            }
            else{
                console.log(responseBody)
            }
        }, setIsLoading);
        // success
        if (response !== undefined) {
            setIsAddingVoucher(false);
            setIsLoading(false);
            Alert.alert('Success', 'Admin added successfully');
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
            <CustomizedButton buttonStyle={styles.voucherButton} text={"Add voucher"} onPress={()=>{setIsAddingVoucher(true)}} textStyle={styles.editButtonText}></CustomizedButton>
            <CustomizedButton buttonStyle={styles.adminButton} text={"Add admin"} onPress={()=>{setIsAddingAdmin(true)}} textStyle={styles.editButtonText}></CustomizedButton>

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
        fontWeight: 'bold',
    },
    adminButton: {
        backgroundColor: Color.ORANGE, // Blue color
        width: '50%',
        height: 100,
    },
    voucherButton: {
        backgroundColor: Color.SEABLUE, // Blue color
        width: '50%',
        height: 100,
    },

})
export default AdminHome