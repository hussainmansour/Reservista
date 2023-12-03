import React, { useState } from 'react';
import { useNavigation } from '@react-navigation/native';
import CustomTextInput from '../Components/CustomTextInput';
import Checkbox from 'expo-checkbox';
import SmallButton from '../Components/SmallButton';
import {
    View,
    Text,
    ScrollView,
    StyleSheet,
    Alert,
    ActivityIndicator
} from 'react-native';
import { signUp } from "../Utilities/API";

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
                userName: username
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
                    <CustomTextInput 
                        placeholder={'Nationality'}
                        title={'Nationality'}
                        secure={false} 
                        onChangeText={(text) => setNationality(text)} 
                        errorMessage={nationalityError}
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

                <SmallButton text={"Sign up"} handlePressing={handleSignUp} />
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
