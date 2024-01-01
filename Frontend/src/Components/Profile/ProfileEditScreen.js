import React, { useState, useEffect } from 'react';
import { View, Text, Modal, StyleSheet, ScrollView } from 'react-native';
import { Formik } from 'formik';
import * as Yup from 'yup';
import CustomTextInput from '../Inputs/EditTextInput';
import CustomizedButton from '../General/Buttons/CustomizedButton';
import DropdownList from '../General/DropdownList';
import editStyles from '../../Styles/Editstyles';
import axios from 'axios';
import Color from '../../Styles/Color';
import { getBaseURL } from '../../Utilities/New/BaseURL';
import { Layout, Button, useStyleSheet, Datepicker } from '@ui-kitten/components';


// for validation
const validationSchema = Yup.object().shape({
    firstName: Yup.string()
        .required('First Name is required')
        .min(2, 'First Name must be at least 2 characters')
        .max(50, 'First Name must be at most 50 characters'),
    lastName: Yup.string()
        .required('Last Name is required')
        .min(2, 'Last Name must be at least 2 characters')
        .max(50, 'Last Name must be at most 50 characters'),
    birthDate: Yup.string()
        .required('Date of birth is required')
        .matches(
            /^\d{4}-\d{2}-\d{2}$/,
            'Invalid date format. Please use YYYY-MM-DD.'
        ),
    gender: Yup.string().required('Gender is required'),
    nationality: Yup.string().required('Nationality is required'),
});


const ProfileEditScreen = ({ isVisible, onSave, onCancel, user }) => {

    const { userName, email, ...updateduser } = user;

    const [countries, setCountries] = useState([])

    console.log(user);

    const formattedDateString = user.birthDate

    let parts = formattedDateString.split("-");
    let formattedDate = new Date(parts[0], parts[1] - 1, parts[2]);

    const [birthDate, setBirthDate] = useState(formattedDate)

    // Get the valid countries

    useEffect(() => {
        const fetchCountries = async () => {
            try {
                const response = await axios.get(`${getBaseURL()}/config/countries`);
                const unsortedCountries = response.data
                setCountries(unsortedCountries.sort());
            } catch (error) {
                console.log('Error fetching Countries:', error);
            }
        };

        fetchCountries();
    }, []);

    const dropdownItems = countries.map(nationality => ({
        label: nationality,
        value: nationality,
    }));
    return (
        <Modal visible={isVisible} animationType="slide" transparent={true}>
            <View
                style={editStyles.modalContainer}>
                <View style={editStyles.modalContent}>
                    <Text style={editStyles.modalTitle}>Edit Profile</Text>

                    <Formik
                        initialValues={updateduser}
                        validationSchema={validationSchema}
                        onSubmit={onSave}
                    >
                        {({ handleChange, handleBlur, handleSubmit, values, errors }) => (
                            <ScrollView showsVerticalScrollIndicator={false}>

                                <CustomTextInput
                                    title="First Name"
                                    onChangeText={handleChange('firstName')}
                                    onBlur={handleBlur('firstName')}
                                    errorMessage={errors.firstName}
                                    Value={values.firstName}
                                />

                                <CustomTextInput
                                    title="Middle Name (Optional)"
                                    onChangeText={handleChange('middleName')}
                                    onBlur={handleBlur('middleName')}
                                    errorMessage={errors.middleName}
                                    Value={values.middleName}
                                />

                                <CustomTextInput
                                    title="Last Name"
                                    onChangeText={handleChange('lastName')}
                                    onBlur={handleBlur('lastName')}
                                    errorMessage={errors.lastName}
                                    Value={values.lastName}
                                />

                                {/* <CustomTextInput
                                    title="Date of birth"
                                    onChangeText={handleChange('birthDate')}
                                    onBlur={handleBlur('birthDate')}
                                    errorMessage={errors.birthDate}
                                    Value={values.birthDate}
                                /> */}

                                <View style={{ marginBottom: 15, marginLeft: 21, width: '85%' }}>
                                    <Text style={{
                                        color: Color.MIDNIGHTBLUE,
                                        fontWeight: 'bold',
                                        marginBottom: 5,
                                        paddingLeft: 10,
                                        fontSize: 15,
                                    }}>
                                        Date of birth
                                    </Text>
                                    <Datepicker
                                        placeholder="Select Date"
                                        date={birthDate}
                                        onSelect={(date) => {
                                            console.log('====================================');
                                            setBirthDate(date);
                                            let validDate = date.getFullYear() +
                                                '-' +
                                                (String(date.getMonth() + 1).padStart(2, '0')) +
                                                '-' +
                                                (String(date.getDate()).padStart(2, '0'));
                                            // values.birthDate=validDate;
                                            console.log(validDate);
                                            handleChange('birthDate')(validDate);
                                            console.log(date);
                                            console.log('====================================');
                                        }}
                                        controlStyle={{
                                            backgroundColor: '#D9D9D9',
                                            borderRadius: 10,
                                            height: 55,
                                            paddingLeft: 10,
                                        }}
                                        min={new Date(1900, 1, 1)}
                                        max={new Date()}
                                    />

                                    {errors.birthDate ? (
                                        <Text style={{
                                            color: 'red',
                                            fontSize: 14,
                                            marginTop: 5,
                                            marginLeft: 10,
                                        }}>{errors.birthDate}</Text>
                                    ) : null}
                                </View>

                                <DropdownList
                                    label="Gender"
                                    selectedValue={values.gender}
                                    onValueChange={(itemValue) => handleChange('gender')(itemValue)}
                                    onBlur={() => handleBlur('gender')}
                                    items={[
                                        { label: 'Male', value: 'MALE' },
                                        { label: 'Female', value: 'FEMALE' },
                                        { label: 'Prefer not to say', value: 'PREFER_NOT_TO_SAY' },
                                    ]}
                                />

                                <DropdownList
                                    label="Nationality"
                                    selectedValue={values.nationality}
                                    onValueChange={(itemValue) => handleChange('nationality')(itemValue)}
                                    onBlur={() => handleBlur('nationality')}
                                    items={dropdownItems}
                                />

                                <View style={editStyles.buttonContainer}>

                                    {/* Cancel Button */}
                                    <CustomizedButton
                                        onPress={onCancel}
                                        buttonStyle={{ ...editStyles.cancelButton, backgroundColor: Color.ORANGE }}
                                        textStyle={editStyles.buttonText}
                                        text="Cancel"
                                    />
                                    {/* Save Button */}
                                    <CustomizedButton
                                        onPress={() => {
                                            onSave(values)
                                        }}
                                        buttonStyle={editStyles.saveButton}
                                        textStyle={editStyles.buttonText}
                                        text="Save"
                                    />

                                </View>
                            </ScrollView>
                        )}
                    </Formik>
                </View>
            </View>
        </Modal>
    );
};



export default ProfileEditScreen;
