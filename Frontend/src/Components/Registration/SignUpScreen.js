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
import {LinearGradient} from 'expo-linear-gradient';



const validationSchema = Yup.object().shape({
    firstName: Yup.string()
        .required('First Name is required')
        .min(2, 'First Name must be at least 2 characters')
        .max(50, 'First Name must be at most 50 characters'),
    lastName: Yup.string()
        .required('Last Name is required')
        .min(2, 'Last Name must be at least 2 characters')
        .max(50, 'Last Name must be at most 50 characters'),
    date: Yup.string()
        .required('Date of birth is required')
        .matches(
            /^\d{4}-\d{2}-\d{2}$/,
            'Invalid date format. Please use YYYY-MM-DD.'
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
    nationality: Yup.string()
        .required('Nationality is required'),
    gender: Yup.string()
        .required("Gender is required")
});

const SignupScreen = () => {
    const [loading, setLoading] = useState('');
    const [countries, setCountries] = useState([]);
    const [agreeTerms, setAgreeTerms] = useState(false);

    const navigation = useNavigation();

    const formik = useFormik({
        initialValues: {
            firstName: '',
            middleName: '',
            lastName: '',
            email: '',
            password: '',
            date: '',
            userName: '',
            nationality: '',
            gender: '',
        },
        // validationSchema,
        onSubmit: async (values) => {
            if(!agreeTerms) {
                Alert.alert('', 'Please agree to the Terms and Conditions');
                return;
            }
            setLoading(true);
            try {
                const response = await signUp(values, setLoading);
                console.log(response.status);
                if (response.status === 200) {
                    Alert.alert('', response.message);
                    navigation.navigate('Verification Code', { email: values.email });
                } else if (response.status === 400) {
                    handleValidationErrors(response.data);
                } else if (response.status === 500) {
                    Alert.alert('', response.message);
                }
            } catch (error) {
                console.error('Error signing up:', error.message);
                Alert.alert('Error', 'An error occurred during signup. Please try again.');
            } finally {
                setLoading(false);
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
                const response = await axios.get('http://192.168.1.17:8080/config/countries');
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

        // fetchCountries();
        addCountries(); //For testing purposes
    }, []);

    const dropdownItems = countries.map((nationality) => ({
        label: nationality,
        value: nationality,
    }));

    return (
        <LinearGradient
            colors={[colors.MIDNIGHTBLUE, colors.SEABLUE]} // Define your gradient colors
            style={{flex: 1}}
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

                        <CustomTextInput
                            placeholder={'YYYY-MM-DD'}
                            title={'Date of birth'}
                            secure={false}
                            onChangeText={formik.handleChange('date')}
                            onBlur={formik.handleBlur('date')}
                            value={formik.values.date}
                            errorMessage={formik.errors.date}
                        />

                        <DropdownList
                            label="Gender"
                            selectedValue={formik.values.gender}
                            onValueChange={(itemValue) => handleChange('gender')(itemValue)}
                            onBlur={() => formik.handleBlur('gender')}
                            items={[
                                { label: 'Male', value: 'MALE' },
                                { label: 'Female', value: 'FEMALE' },
                                { label: 'Prefer not to say', value: 'PREFER_NOT_TO_SAY' },
                            ]}
                            color={'white'}
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
                            keyboardType="email-address"
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
                            keyboardType="visible-password"
                        />

                        <DropdownList
                            label="Nationality"
                            selectedValue={formik.values.nationality}
                            onValueChange={(itemValue) => formik.setFieldValue('nationality', itemValue)}
                            onBlur={() => formik.handleBlur('nationality')}
                            items={dropdownItems}
                            color={'white'}
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
