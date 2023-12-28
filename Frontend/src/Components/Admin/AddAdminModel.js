import { Modal, StyleSheet, Text, View} from "react-native";
import styles from "../../Styles/Editstyles";
import {Formik} from "formik";
import CustomTextInput from "../Inputs/EditTextInput";
import CustomizedButton from "../General/Buttons/CustomizedButton";
import React, {useState} from "react";
import * as Yup from "yup";
import Color from "../../Styles/Color";



// for validation
const validationSchema = Yup.object().shape({
    adminName: Yup.string()
        .required('Username is required')
        .min(2, 'Username must be at least 2 characters')
        .max(50, 'Username must be at most 50 characters'),
    password: Yup.string()
        .required('Password percentage is required')
        .min(8, 'Password length must be greater 8')
        .max(100, 'Password length must be greater 5'),
});

const AddAdminModel = ({ isVisible, onCancel ,onSave}) => {

    return(
        <Modal visible={isVisible} animationType="slide" transparent={false}>
            <View style={{backgroundColor:Color.PALEBLUE,  flex:1, justifyContent: "center",paddingTop:50}}>
                <Text style={styles.modalTitle}>Add Admin</Text>
                <Formik
                    initialValues={{
                        adminName: '',
                        password: '',
                    }}
                    validationSchema={validationSchema}
                    onSubmit={onSave}
                >
                    {({ handleChange, handleBlur, values, errors ,isValid,touched }) => (
                        <View>
                            <CustomTextInput
                                title="adminName"
                                onChangeText={handleChange('adminName')}
                                onBlur={handleBlur('adminName')}
                                errorMessage={errors.adminName}
                                Value={values.adminName}
                                textStyle={{color:Color.MIDNIGHTBLUE}}
                                textInputStyle={{backgroundColor:Color.MIDNIGHTBLUE}}

                            />
                            <CustomTextInput
                                title="Password"
                                onChangeText={handleChange('password')}
                                onBlur={handleBlur('password')}
                                errorMessage={errors.password}
                                Value={values.password}
                            />

                            <View style={styles.buttonContainer}>

                                <CustomizedButton
                                    onPress={onCancel}
                                    buttonStyle={{backgroundColor:Color.ORANGE}}
                                    textStyle={styles.buttonText}
                                    text="Cancel"
                                />

                                <CustomizedButton
                                    onPress={()=>{
                                        onSave(values)
                                    }}
                                    isDisabled={!isValid || !touched.adminName}
                                    buttonStyle={{backgroundColor: Color.SEABLUE,opacity: (!isValid || !touched.adminName) ? 0.5 : 1 }}
                                    textStyle={styles.buttonText}
                                    text="Save"
                                />
                            </View>
                        </View>
                    )}
                </Formik>
            </View>
        </Modal>
    );
}
const style= StyleSheet.create({

});
export default AddAdminModel;
