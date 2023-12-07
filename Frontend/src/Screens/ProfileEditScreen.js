import React from 'react';
import { View, Text, TextInput, Button, Modal, StyleSheet, ScrollView, KeyboardAvoidingView, Platform, TouchableOpacity } from 'react-native';
import { Picker } from '@react-native-picker/picker';
import { Formik } from 'formik';
import * as Yup from 'yup';
import CustomTextInput from '../Components/EditTextInput';

const validationSchema = Yup.object().shape({
    username: Yup.string()
        .required('Username is required')
        .min(4, 'Username must be at least 4 characters')
        .max(20, 'Username must be at most 20 characters'),
    firstName: Yup.string().required('First Name is required'),
    lastName: Yup.string().required('Last Name is required'),
    email: Yup.string()
        .email('Invalid email')
        .required('Email is required'),
    phone: Yup.string()
        .matches(/^\d{11}$/, 'Invalid phone number')
        .required('Phone is required'),
    gender: Yup.string().oneOf(['Male', 'Female'], 'Invalid gender'),
    nationality: Yup.string(),
    passportNumber: Yup.string()
        .matches(/^[A-Za-z0-9]+$/, 'Invalid passport number')
        .min(6, 'Passport number must be at least 6 characters')
        .max(15, 'Passport number must be at most 15 characters')
        .required('Passport number is required'),
});


const ProfileEditScreen = ({ isVisible, onSave, onCancel, user }) => {
    return (
        <Modal visible={isVisible} animationType="slide" transparent={true}>
            <View
                style={styles.modalContainer}>
                <View style={styles.modalContent}>
                    <Text style={styles.modalTitle}>Edit Profile</Text>

                    <Formik
                        initialValues={user}
                        validationSchema={validationSchema}
                        onSubmit={onSave}
                    >
                        {({ handleChange, handleBlur, handleSubmit, values, errors }) => (
                            <ScrollView>
                                <CustomTextInput
                                    title="Username"
                                    onChangeText={handleChange('username')}
                                    onBlur={handleBlur('username')}
                                    errorMessage={errors.username}
                                    Value={values.username}
                                />

                                <CustomTextInput
                                    title="First Name"
                                    onChangeText={handleChange('firstName')}
                                    onBlur={handleBlur('firstName')}
                                    errorMessage={errors.firstName}
                                    Value={values.firstName}
                                />

                                <CustomTextInput
                                    title="Last Name"
                                    onChangeText={handleChange('lastName')}
                                    onBlur={handleBlur('lastName')}
                                    errorMessage={errors.lastName}
                                    Value={values.lastName}
                                />

                                <CustomTextInput
                                    title="Email"
                                    onChangeText={handleChange('email')}
                                    onBlur={handleBlur('email')}
                                    errorMessage={errors.email}
                                    Value={values.email}
                                />

                                <CustomTextInput
                                    title="Phone"
                                    onChangeText={handleChange('phone')}
                                    onBlur={handleBlur('phone')}
                                    errorMessage={errors.phone}
                                    Value={values.phone}
                                    type="numeric"
                                />

                                {/* Gender Dropdown */}
                                <View style={styles.fieldContainer}>
                                    <Text style={styles.fieldLabel}>Gender:</Text>
                                    <View style={styles.input}>
                                        <Picker
                                            selectedValue={values.gender}
                                            dropdownIconColor={'gray'}
                                            onValueChange={(itemValue) => handleChange('gender')(itemValue)}
                                            onBlur={handleBlur('gender')}
                                        >
                                            <Picker.Item label="Male" value="Male" />
                                            <Picker.Item label="Female" value="Female" />
                                        </Picker>
                                        {errors.gender && (
                                            <Text style={styles.errorText}>{errors.gender}</Text>
                                        )}
                                    </View>
                                </View>

                                {/* Nationality Dropdown */}
                                <View style={styles.fieldContainer}>
                                    <Text style={styles.fieldLabel}>Nationality:</Text>
                                    <View style={styles.input}>
                                        <Picker
                                            selectedValue={values.nationality}
                                            dropdownIconColor={'gray'}
                                            onValueChange={(itemValue) => handleChange('nationality')(itemValue)}
                                            onBlur={handleBlur('nationality')}
                                        >
                                            <Picker.Item label="Egypt" value="EG" />
                                            <Picker.Item label="United States" value="US" />
                                            <Picker.Item label="United Kingdom" value="UK" />
                                            <Picker.Item label="Australia" value="AU" />
                                            <Picker.Item label="Canada" value="CA" />
                                            <Picker.Item label="France" value="FR" />
                                            <Picker.Item label="Germany" value="DE" />
                                            <Picker.Item label="Italy" value="IT" />
                                            <Picker.Item label="Japan" value="JP" />
                                            <Picker.Item label="Korea" value="KR" />
                                            <Picker.Item label="Russia" value="RU" />
                                            <Picker.Item label="Spain" value="ES" />
                                            <Picker.Item label="Sweden" value="SE" />
                                            <Picker.Item label="Switzerland" value="CH" />
                                            <Picker.Item label="United Arab Emirates" value="AE" />
                                            <Picker.Item label="China" value="CN" />
                                            <Picker.Item label="India" value="IN" />
                                            <Picker.Item label="Indonesia" value="ID" />

                                        </Picker>
                                        {errors.nationality && (
                                            <Text style={styles.errorText}>{errors.nationality}</Text>
                                        )}
                                    </View>
                                </View>

                                <CustomTextInput
                                    title="Passport Number"
                                    onChangeText={handleChange('passportNumber')}
                                    onBlur={handleBlur('passportNumber')}
                                    errorMessage={errors.passportNumber}
                                    Value={values.passportNumber}
                                />

                                <View style={styles.buttonContainer}>
                                    <TouchableOpacity
                                        style={styles.saveButton}
                                        onPress={handleSubmit}
                                    >
                                        <Text style={styles.buttonText}>Save</Text>
                                    </TouchableOpacity>

                                    <TouchableOpacity
                                        style={styles.cancelButton}
                                        onPress={onCancel}
                                    >
                                        <Text style={styles.buttonText}>Cancel</Text>
                                    </TouchableOpacity>
                                </View>
                            </ScrollView>
                        )}
                    </Formik>
                </View>
            </View>
        </Modal>
    );
};


const styles = StyleSheet.create({
    modalContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center'
    },
    modalContent: {
        backgroundColor: 'white',
        padding: 20,
        flex: 1,
        width: '100%',
    },
    modalTitle: {
        marginBottom: 10,
        textAlign: 'center',
        fontFamily: "Roboto",
        fontSize: 22,
        fontWeight: "400",
        letterSpacing: 0,
        lineHeight: 28,
    },
    fieldContainer: {
        marginBottom: 30,
        marginLeft: 20,
        borderRadius: 10
    },
    fieldLabel: {
        color: 'black',
        fontWeight: 'bold',
        marginBottom: 5,
        paddingLeft: 10,
        fontSize: 15,
    },
    input: {
        backgroundColor: '#D9D9D9',
        borderRadius: 10,
        width: '90%',
        height: 55,
        paddingLeft: 10,
    },
    picker: {
        height: 40,
        borderColor: 'gray',
        borderWidth: 1,
        backgroundColor: '#D9D9D9',
    },
    pickerItem: {
        height: 20,
        fontSize: 20,
    },
    errorInput: {
        borderColor: 'red',
    },
    errorText: {
        color: 'red',
        fontSize: 12,
        marginLeft: 10,
    },
    buttonContainer: {
        flexDirection: 'row',
        justifyContent: 'space-around',
        marginTop: 20,
        paddingBottom:20
    },
    saveButton: {
        backgroundColor: '#4CAF50', // Green color
        paddingVertical: 10,
        paddingHorizontal: 20,
        borderRadius: 5,
        width:'30%',
        height:50,
    },
    cancelButton: {
        backgroundColor: '#A9A9A9', // Gray color
        paddingVertical: 10,
        paddingHorizontal: 20,
        borderRadius: 5,
        width:'30%',
        height:50,
    },
    buttonText: {
        color: 'white',
        fontSize: 16,
        fontWeight: 'bold',
        textAlign: 'center',
    },
});

export default ProfileEditScreen;
