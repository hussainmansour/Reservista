import React, { useState } from 'react';
import Checkbox from 'expo-checkbox';
import {
    View,
    Text,
    TextInput,
    TouchableOpacity,
    ScrollView,
    StyleSheet,
    Alert,
} from 'react-native';


const VerificationCodeScreen = ({ navigation}) => {
    const [enteredVerificationCode, setEnteredVerificationCode] = useState('');
    const [verificationCode, setVerificationCode] = useState('');

    const handleVerifyCode = () => {
        // Compare the entered code with the generated code
        if (verificationCode === enteredVerificationCode) {
             console.log('Email successfully verified!');

             navigation.navigate('Login');
        } else {
            // Incorrect code, show an error message
            Alert.alert('Error', 'Incorrect verification code. Please try again.');
        }
    };
    const generateVerificationCode = () => {
        // Generate a random 6-digit verification code
        const code = Math.floor(100000 + Math.random() * 900000).toString();
        setVerificationCode(code);

        // Send the code to the user's email (simulated here)
        console.log(`Verification code sent to email: ${code}`);
    };

    const handleResendCode = () => {
        // Resend the verification code
        generateVerificationCode();

        // Show a message indicating that a new code has been sent
        Alert.alert('Code Resent', 'A new verification code has been sent to your email.');
    };
    return (
        <ScrollView contentContainerStyle={styles.scrollContainer}>
            {/*generateVerificationCode();*/}
            <View style={styles.wholeForm}>
                <View style={styles.inputContainer}>
                    <Text style={styles.text}>Enter Verification Code</Text>
                    <TextInput
                        placeholder='123456'
                        style={styles.textInput}
                        keyboardType='numeric'
                        onChangeText={(text) => setEnteredVerificationCode(text)}
                    />
                </View>

                {/* Verify Code Button */}
                <TouchableOpacity
                    style={styles.loginButtonContainer}
                    onPress={() => handleVerifyCode()}
                >
                    <Text style={styles.LoginText}>Verify Code</Text>
                </TouchableOpacity>

                {/* Resend Code Button */}
                <TouchableOpacity
                    style={styles.resendButtonContainer}
                    onPress={() => handleResendCode()}
                >
                    <Text style={styles.resendText}>Resend Code</Text>
                </TouchableOpacity>
            </View>
        </ScrollView>
    );
};

// Style
export default VerificationCodeScreen;

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