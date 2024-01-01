import React, { useState, useEffect } from 'react';
import { useNavigation } from '@react-navigation/native';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import {
    View,
    Text,
    ScrollView,
    Alert,
    ActivityIndicator,
} from 'react-native';
import CustomTextInput from '../Inputs/CustomTextInput';
import Checkbox from 'expo-checkbox';
import { signUp } from '../../Utilities/API';
import CustomizedButton from '../General/Buttons/CustomizedButton';
import DropdownList from '../General/DropdownList';
import axios from "axios";
import styles from '../../Styles/SignUpstyles';
import Loginstyles from "../../Styles/Loginstyles";
import colors from "../../Styles/Color";
import { LinearGradient } from 'expo-linear-gradient';
import { RegistrationAPI } from "../../Utilities/New/APIs/RegistrationAPI";
import Color from '../../Styles/Color';
import { Layout, Button, useStyleSheet, Datepicker } from '@ui-kitten/components';
import { getBaseURL } from '../../Utilities/New/BaseURL';



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
            'Invalid Date format. Please use YYYY-MM-DD.'
        ),
    email: Yup.string()
        .required('Email is required')
        .email('Please enter a valid Gmail address')
        .matches(/@gmail.com$/, 'Please enter a valid Gmail address'),
    userName: Yup.string()
        .required('Username is required')
        .min(1, 'Username must be at least 6 characters'),
    password: Yup.string()
        .required('Password is required')
        .min(8, 'Password must be at least 8 characters')
        .matches(
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\da-zA-Z]).{8,}$/,
            'Password must contain at least one lowercase letter, one uppercase letter, one number, and one special character'
        ),
});

const SignupScreen = () => {
    const [loading, setLoading] = useState('');
    const [countries, setCountries] = useState([]);
    const [agreeTerms, setAgreeTerms] = useState(false);
    const [birthDate, setBirthDate] = useState(null)


    const navigation = useNavigation();

    const errorCodes = {
        EMAIL_ALREADY_EXISTS: 1,
        USERNAME_ALREADY_EXISTS: 2,
        ACCOUNT_DEACTIVATED: 3,
        ACCOUNT_BLOCKED: 4,
        REGISTRATION_RACE_CONDITION: 5,
    }

    const formik = useFormik({
        initialValues: {
            firstName: '',
            middleName: '',
            lastName: '',
            email: '',
            password: '',
            birthDate: '',
            userName: '',
            nationality: '',
            gender: 'MALE',
        },
        validationSchema,
        onSubmit: async (values) => {
            if (!agreeTerms) {
                Alert.alert('', 'Please agree to the Terms and Conditions');
                return;
            }
            console.log("nat" + values.nationality + "gender" + values.gender)
            let response = await RegistrationAPI.register(values, (response) => {
                // in case of an expected error this should be the errorDTO
                const responseBody = response.data;

                if (responseBody.data !== undefined) {
                    if (responseBody.errorCode === errorCodes.ACCOUNT_DEACTIVATED) {
                        Alert.alert('Reminder', responseBody.data);
                        navigation.navigate('Verification Code', { email: values.email });
                    } else {
                        Alert.alert('Error', responseBody.data);
                    }
                } else {
                    // if it doesn't have the data attribute then it's not the errorDTO
                    // so, it's an unhandled exception
                    console.log(responseBody)
                }

            }, setLoading);

            // success
            if (response !== undefined) {
                Alert.alert('Success', 'Please check your email for the verification code.');
                navigation.navigate('Verification Code', { email: values.email });
            }
        },
    });

    const handleValidationErrors = (errorData) => {
        Object.keys(errorData).forEach((key) => {
            formik.setFieldError(key, errorData[key]);
        });
    };

    useEffect(() => {
        const fetchCountries = async () => {
            try {
                const response = await axios.get(`${getBaseURL()}/config/countries`);
                const unsortedCountries = response.data;
                setCountries(unsortedCountries.sort());
            } catch (error) {
                console.log('Error fetching Countries:', error);
            }
        };

        //For testing purposes
        const addCountries = async () => {
            try {
                let tempCountries = ['Egypt', 'Algeria', 'Andorra', 'Angola'];
                setCountries(tempCountries.sort());
            } catch (error) {
                console.log('Error adding Countries:', error);
            }
        };

        fetchCountries();
        // addCountries(); //For testing purposes
    }, []);

    const dropdownItems = countries.map((nationality) => ({
        label: nationality,
        value: nationality,
    }));

    return (
        <LinearGradient
            colors={[colors.MIDNIGHTBLUE, colors.SEABLUE]} // Define your gradient colors
            style={{ flex: 1 }}
        >
            <ScrollView contentContainerStyle={styles.scrollContainer}>
                <View style={styles.wholeForm}>
                    <View style={styles.sectionContainer}>
                        <Text style={styles.sectionTitle}>Personal Information</Text>

                        <CustomTextInput
                            placeholder={'First name'}
                            title={'First name'}
                            secure={false}
                            onChangeText={formik.handleChange('firstName')}
                            onBlur={formik.handleBlur('firstName')}
                            value={formik.values.firstName}
                            errorMessage={formik.errors.firstName}
                        />
                        <CustomTextInput
                            placeholder={'Middle name'}
                            title={'Middle name'}
                            secure={false}
                            onChangeText={formik.handleChange('middleName')}
                            onBlur={formik.handleBlur('middleName')}
                            value={formik.values.middleName}
                            errorMessage={formik.errors.middleName}
                        />

                        <CustomTextInput
                            placeholder={'Last name'}
                            title={'Last name'}
                            secure={false}
                            onChangeText={formik.handleChange('lastName')}
                            onBlur={formik.handleBlur('lastName')}
                            value={formik.values.lastName}
                            errorMessage={formik.errors.lastName}
                        />

                        {/* <CustomTextInput
                            placeholder={'YYYY-MM-DD'}
                            title={'Date of birth'}
                            secure={false}
                            onChangeText={formik.handleChange('birthDate')}
                            onBlur={formik.handleBlur('birthDate')}
                            value={formik.values.birthDate}
                            errorMessage={formik.errors.birthDate}
                        /> */}
                        <View style={{ marginBottom: 15, marginLeft: 21, width: '85%' }}>
                            <Text style={{
                                color: 'white',
                                fontWeight: 'bold',
                                marginBottom: 5,
                                paddingLeft: 10,
                                fontSize: 17,
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
                                    formik.handleChange('birthDate')(validDate);
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

                            {formik.errors.birthDate ? (
                                <Text style={{
                                    color: 'red',
                                    fontSize: 14,
                                    marginTop: 5,
                                    marginLeft: 10,
                                }}>{formik.errors.birthDate}</Text>
                            ) : null}
                        </View>



                        <DropdownList
                            label="Gender"
                            selectedValue={formik.values.gender}
                            onValueChange={(itemValue) => formik.setFieldValue('gender', itemValue)}
                            onBlur={() => formik.handleBlur('gender')}
                            items={[
                                { label: 'Male', value: 'MALE' },
                                { label: 'Female', value: 'FEMALE' },
                                { label: 'Prefer not to say', value: 'PREFER_NOT_TO_SAY' },
                            ]}
                            color={'white'}
                            errorMessage={formik.errors.gender}
                        />
                    </View>

                    <View style={styles.sectionContainer}>
                        <Text style={styles.sectionTitle}>Contact Information</Text>

                        <CustomTextInput
                            placeholder={'example@gmail.com'}
                            title={'Email'}
                            secure={false}
                            onChangeText={formik.handleChange('email')}
                            onBlur={formik.handleBlur('email')}
                            value={formik.values.email}
                            errorMessage={formik.errors.email}
                            type="email-address"
                        />
                        <CustomTextInput
                            placeholder={'Username'}
                            title={'Username'}
                            secure={false}
                            onChangeText={formik.handleChange('userName')}
                            onBlur={formik.handleBlur('userName')}
                            value={formik.values.userName}
                            errorMessage={formik.errors.userName}
                        />
                        <CustomTextInput
                            placeholder={'Password (8 characters)'}
                            title={'Password'}
                            secure={true}
                            onChangeText={formik.handleChange('password')}
                            onBlur={formik.handleBlur('password')}
                            value={formik.values.password}
                            errorMessage={formik.errors.password}
                        />

                        <DropdownList
                            label="Nationality"
                            selectedValue={formik.values.nationality}
                            onValueChange={(itemValue) => formik.setFieldValue('nationality', itemValue)}
                            onBlur={() => formik.handleBlur('nationality')}
                            items={dropdownItems}
                            color={'white'}
                            errorMessage={formik.errors.nationality}

                        />
                    </View>

                    <View style={styles.termsContainer}>
                        <Checkbox
                            value={agreeTerms}
                            onValueChange={() => setAgreeTerms(!agreeTerms)}
                            style={styles.checkbox}
                            uncheckedColor='white'
                            status={agreeTerms ? 'checked' : 'unchecked'}
                            onPress={() => setAgreeTerms(!agreeTerms)}
                            color='#022D41' // Customize the checked color
                        />
                        <Text style={styles.termsText}>
                            I agree to the{' '}
                            <Text style={styles.linkText} onPress={() => navigation.navigate('Terms And Conditions')}>
                                Terms and Conditions
                            </Text>
                        </Text>
                    </View>

                    {loading && <ActivityIndicator size="large" color="#0000ff" />}

                    <CustomizedButton
                        text={'Sign Up'}
                        onPress={formik.handleSubmit}
                        buttonStyle={Loginstyles.loginButton} // Add custom style for the Sign Up button
                        textStyle={Loginstyles.loginButtonText} // Add custom style for the Sign Up button text
                    />
                </View>
            </ScrollView>
        </LinearGradient>
    );
};

export default SignupScreen;
