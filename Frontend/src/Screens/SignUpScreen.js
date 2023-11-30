import React, {useState, useEffect} from 'react';
import { useNavigation } from '@react-navigation/native';
import axios from 'axios';
import CustomTextInput from '../Components/CustomTextInput';
import Checkbox from 'expo-checkbox';
import SmallButton from '../Components/SmallButton';
import {
    View,
    Text,
    TextInput,
    TouchableOpacity,
    ScrollView,
    StyleSheet,
    Alert,
    Button,
    Image, ActivityIndicator
} from 'react-native';
import {signUp} from "../Utilities/API";



const SignupScreen = () => {
  const [firstName, firstNameSet] = useState('');
  const [lastName , lastNameSet ] = useState('');
  const [email, emailSet] = useState('');
  const [password, passwordSet] = useState('');
  const [date, dateSet] = useState('');
  const [nationality, nationalitySet] = useState('');
  const [username, usernameSet] = useState('');
  const [agreeTerms, setAgreeTerms] = useState(false);
  const [verificationCode, setVerificationCode] = useState('');
  const [enteredVerificationCode, setEnteredVerificationCode] = useState('');
  const [loading, setLoading] = useState(false);

    const url = "http://192.168.1.4:8080"

  const navigation = useNavigation();
    const handleSignUp = () => {
        if (agreeTerms) {
            const userData = {
                firstName,
                lastName,
                email,
                password,
                date,
                nationality,
                username
            };
            const response = signUp(userData , setLoading);
            console.log(response)
            // Navigate to the verification code screen
            navigation.navigate('VerificationCode');
        } else {
            Alert.alert('Error', 'Please agree to the Terms and Conditions before signing up.');
        }
    };

    return (
        <ScrollView contentContainerStyle={styles.scrollContainer}>
            <View style={styles.wholeForm}>
                {/* Personal Information */}
                <View style={styles.sectionContainer}>
                    <Text style={styles.sectionTitle}>Personal Information</Text>

                    <View style={styles.inputContainer}>
                        <Text style={styles.text}>First name</Text>
                        <TextInput
                            placeholder='First name'
                            style={styles.textInput}
                            accessibilityLabel='First name'
                            onChangeText={(text) => firstNameSet(text)}
                        />
                    </View>

                    <View style={styles.inputContainer}>
                        <Text style={styles.text}>Last name</Text>
                        <TextInput
                            placeholder='Last name'
                            style={styles.textInput}
                            accessibilityLabel='Last name'
                            onChangeText={(lName) => lastNameSet(lName)}
                        />
                    </View>

                    <View style={styles.inputContainer}>
                        <Text style={styles.text}>Date of birth</Text>
                        <TextInput
                            placeholder='DD/MM/YYYY'
                            style={styles.textInput}
                            keyboardType='numeric'
                            accessibilityLabel='Date of birth'
                            onChangeText={(date) => dateSet(date)}
                        />
                    </View>
                </View>

                {/* Contact Information */}
                <View style={styles.sectionContainer}>
                    <Text style={styles.sectionTitle}>Contact Information</Text>

                    <View style={styles.inputContainer}>
                        <Text style={styles.text}>Email</Text>
                        <TextInput
                            placeholder='example@gmail.com'
                            style={styles.textInput}
                            keyboardType='email-address'
                            accessibilityLabel='Email'
                            onChangeText={(email) => emailSet(email)}
                        />
                    </View>

                    <View style={styles.inputContainer}>
                        <Text style={styles.text}>Username</Text>
                        <TextInput
                            placeholder='Username'
                            style={styles.textInput}
                            accessibilityLabel='Username'
                            onChangeText={(username) => usernameSet(username)}
                        />
                    </View>

                    <View style={styles.inputContainer}>
                        <Text style={styles.text}>Password</Text>
                        <TextInput
                            placeholder='Password (8 characters)'
                            style={styles.textInput}
                            secureTextEntry={true}
                            accessibilityLabel='Password'
                            onChangeText={(pass) => passwordSet(pass)}
                        />
                    </View>

                    <View style={styles.inputContainer}>
                        <Text style={styles.text}>Nationality</Text>
                        <TextInput
                            placeholder='Nationality'
                            style={styles.textInput}
                            accessibilityLabel='Nationality'
                            onChangeText={(text) => nationalitySet(text)}
                        />
                    </View>
                </View>
                {/* Terms and Conditions */}
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
                <TouchableOpacity
                    style={styles.loginButtonContainer}
                    onPress={() => handleSignUp()}
                >
                    <Text style={styles.LoginText}>Sign up</Text>
                </TouchableOpacity>
            </View>
        </ScrollView>
    );
}

export default SignupScreen

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
    inputContainer: {
        marginBottom: 15,
    },
    textInput: {
        backgroundColor: '#D9D9D9',
        borderRadius: 10,
        width: '100%',
        height: 46,
        paddingLeft: 10,
        marginBottom: 10,
    },
    text: {
        color: 'white',
        fontWeight: 'bold',
        fontSize: 20,
    },
    loginButtonContainer: {
        backgroundColor: '#728FF3',
        alignSelf: 'center',
        marginTop: 30,
        width: 118,
        height: 43,
        borderRadius: 10,
        alignItems: 'center',
        justifyContent: 'center',
    },
    LoginText: {
        fontSize: 20,
        color: 'white',
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

    resendButtonContainer: {
        backgroundColor: '#728FF3',
        alignSelf: 'center',
        marginTop: 10,
        width: 118,
        height: 43,
        borderRadius: 10,
        alignItems: 'center',
        justifyContent: 'center',
    },
    resendText: {
        fontSize: 16,
        color: 'white',
    },
});
