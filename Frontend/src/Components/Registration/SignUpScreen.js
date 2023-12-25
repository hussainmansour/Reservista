import React, { useState, useEffect } from 'react';
import { useNavigation } from '@react-navigation/native';
import CustomTextInput from '../Inputs/CustomTextInput';
import Checkbox from 'expo-checkbox';
import {
    View,
    Text,
    ScrollView,
    StyleSheet,
    Alert,
    ActivityIndicator
} from 'react-native';
import { signUp } from "../../Utilities/API";
import CustomizedButton from '../General/Buttons/CustomizedButton';
import DropdownList from '../General/DropdownList';


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
    email: Yup.string()
        .required('Email is required')
        .email('Please enter a valid email address')
        .matches(/@gmail.com$/, 'Please enter a valid Gmail address'),
    userName: Yup.string()
        .required('Username is required')
        .min(1, 'Username must be at least 6 characters'),
    //password is required and must be at least 8 characters and have lowercase, uppercase, number, and special character
    password: Yup.string()
        .required('Password is required')
        .min(8, 'Password must be at least 8 characters')
        .matches(
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\da-zA-Z]).{8,}$/,   //regex for password validation 
            'Password must contain at least one lowercase letter, one uppercase letter, one number, and one special character'
        ),
        nationality: Yup.string()
        .required('Nationality is required')
        
});

            

const SignupScreen = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [date, setDate] = useState('');
    const [username, setUsername] = useState('');
    const [nationality, setNationality] = useState('');
    const [agreeTerms, setAgreeTerms] = useState(false);
    const [loading, setLoading] = useState('');
    const [countries, setCountries] = useState([])

    // Error states for each input field
    const [firstNameError, setFirstNameError] = useState('');
    const [lastNameError, setLastNameError] = useState('');
    const [emailError, setEmailError] = useState('');
    const [passwordError, setPasswordError] = useState('');
    const [dateError, setDateError] = useState('');
    const [usernameError, setUsernameError] = useState('');
    const [nationalityError, setNationalityError] = useState('');

    const navigation = useNavigation();

    const handleSignUp = async () => {
        // Clear previous error messages
        setFirstNameError('');
        setLastNameError('');
        setEmailError('');
        setPasswordError('');
        setDateError('');
        setUsernameError('');

        // Validation checks for each field
        if (!firstName) {
            setFirstNameError('Please enter your first name.');
        }

        if (!lastName) {
            setLastNameError('Please enter your last name.');
        }

        if (!email) {
            setEmailError('Please enter your email.');
        }

        if (!password) {
            setPasswordError('Please enter your password.');
        }

        if (!date) {
            setDateError('Please enter your date of birth.');
        } else if (!/^\d{4}-\d{2}-\d{2}$/.test(date)) {
            setDateError('Date must be in the format YYYY-MM-DD.');
        }

        if (!username) {
            setUsernameError('Please enter your username.');
        }

        if (!nationality) {
            setNationalityError('Please enter your nationality.');
        }

        if (agreeTerms) {
            const userInfo = {
                firstName: firstName,
                lastName: lastName,
                email: email,
                password: password,
                birthDate: date,
                userName: username,
                nationality:nationality
            };

            // Call signUp function only if there are no validation errors
            if (!(firstNameError || lastNameError || emailError || passwordError || dateError || usernameError)) {
                setLoading(true);
                try {
                    const response = await signUp(userInfo, setLoading);
                    console.log(response.status);
                    if (response.status === 200) {
                        Alert.alert('', response.message);
                        navigation.navigate('VerificationCode', {email: email});
                    }else if(response.status === 400){
                        if(response.data.email) setEmailError(response.data.email)
                        if(response.data.nationality) setNationalityError(response.data.nationality)
                        if(response.data.birthDate) setDateError(response.data.birthDate)
                        if(response.data.lastName) setLastNameError(response.data.lastName)
                        if(response.data.firstName) setFirstNameError(response.data.firstName)
                        if(response.data.userName) setUsernameError(response.data.userName)
                        if(response.data.password) setPasswordError(response.data.password)
                    }else if(response.status === 500){
                        Alert.alert('', response.message);
                    }
                } catch (error) {
                    console.error('Error signing up:', error.message);
                    Alert.alert('Error', 'An error occurred during signup. Please try again.');
                } finally {
                    setLoading(false);
                }
            }
        } else {
            Alert.alert('Error', 'Please agree to the Terms and Conditions before signing up.');
        }
    };

    useEffect(() => {
        //Todo: need to refactor this code
        const fetchCountries = async () => {
            try {
                const response = await axios.get("http://192.168.1.5:8080/config/countries");
                const unsortedCountries = response.data
                setCountries(unsortedCountries.sort());
            } catch (error) {
                console.log('Error fetching Countries:', error);
            }
        };

        const addCountries = async () => {
            try {
                let tempCountries = ["Egypt"];
                setCountries(tempCountries.sort());
            } catch (error) { 
                console.log('Error adding Countries:', error);
            }
        };

        addCountries();
        // fetchCountries();
    }, []);

    const dropdownItems = countries.map(nationality => ({
        label: nationality,
        value: nationality,
    }));

    return (
        <ScrollView contentContainerStyle={styles.scrollContainer}>
            <View style={styles.wholeForm}>

                <View style={styles.sectionContainer}>
                    <Text style={styles.sectionTitle}>Personal Information</Text>

                    <CustomTextInput
                        placeholder={'First name'}
                        title={'First name'}
                        secure={false}
                        onChangeText={(text) => setFirstName(text)}
                        errorMessage={firstNameError}
                    />
                    <CustomTextInput
                        placeholder={'Last name'}
                        title={'Last name'}
                        secure={false}
                        onChangeText={(lName) => setLastName(lName)}
                        errorMessage={lastNameError}
                    />

                    <CustomTextInput
                        placeholder={'YYYY-MM-DD'}
                        title={'Date of birth'}
                        secure={false}
                        onChangeText={(date) => setDate(date)}
                        errorMessage={dateError}
                    />

                </View>

                <View style={styles.sectionContainer}>
                    <Text style={styles.sectionTitle}>Contact Information</Text>

                    <CustomTextInput
                        placeholder={'example@gmail.com'}
                        title={'Email'}
                        secure={false}
                        onChangeText={(text) => setEmail(text)}
                        errorMessage={emailError}
                        keyboardType = "email-address"
                    />
                    <CustomTextInput
                        placeholder={'Username'}
                        title={'Username'}
                        secure={false}
                        onChangeText={(uName) => setUsername(uName)}
                        errorMessage={usernameError}
                    />
                    <CustomTextInput
                        placeholder={'Password (8 characters)'}
                        title={'Password'}
                        secure={true}
                        onChangeText={(pass) => setPassword(pass)}
                        errorMessage={passwordError}
                        keyboardType="visible-password"
                    />

                    <DropdownList
                        label="Nationality"

                        items={dropdownItems}
                        color={'white'}
                    />
                   

                </View>

                <View style={styles.termsContainer}>
                    <Checkbox
                        value={agreeTerms}
                        onValueChange={() => setAgreeTerms(!agreeTerms)}
                        style={styles.checkbox}
                    />
                    <Text style={styles.termsText}>
                        I agree to the{' '}
                        <Text
                            style={styles.linkText}
                            onPress={() => navigation.navigate('TermsAndConditions')}
                        >
                            Terms and Conditions
                        </Text>
                    </Text>
                </View>

                {loading && <ActivityIndicator size="large" color="#0000ff" />}

                <CustomizedButton text={"Sign up"} onPress={handleSignUp} />
            </View>
        </ScrollView>
    );
}

export default SignupScreen;

const styles = StyleSheet.create({

    scrollContainer: {
        flexGrow: 1,
    },
    wholeForm: {
        backgroundColor: '#131141',
        flex: 1,
        justifyContent: 'center',
        paddingHorizontal: 20,
    },
    sectionContainer: {
        marginBottom: 20,
    },
    sectionTitle: {
        color: 'white',
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 10,
    },
    termsContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        marginTop: 10,
    },
    checkbox: {
        marginRight: 8,
    },
    termsText: {
        color: 'white',
        fontSize: 16,
    },
    linkText: {
        color: '#728FF3',
        textDecorationLine: 'underline',
    },
});
