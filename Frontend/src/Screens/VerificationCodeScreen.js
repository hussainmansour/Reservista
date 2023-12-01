import React, { useState } from 'react';
import Checkbox from 'expo-checkbox';
import {
    View,
    Text,
    TextInput,
    TouchableOpacity,
    ScrollView,
    StyleSheet,
    Alert, ActivityIndicator,
} from 'react-native';
import CustomTextInput from '../Components/CustomTextInput';
import SmallButton from '../Components/SmallButton';


const VerificationCodeScreen = ({ navigation}) => {
    const [enteredVerificationCode, setEnteredVerificationCode] = useState('');
    const [verificationCode, setVerificationCode] = useState('');
    const [loading, setLoading] = useState(false);

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
    
    const handleResendCode = () => {
        Alert.alert('Code Resent', 'A new verification code has been sent to your email.');
    };
    
    return (
        <ScrollView contentContainerStyle={styles.scrollContainer}>
            <View style={styles.wholeForm}>
                <CustomTextInput placeholder={'123456'} title={'Enter Verification Code'} secure = {false} onChangeText={(text) => setEnteredVerificationCode(text)}/>
                
                {loading && <ActivityIndicator size="large" color="#0000ff" />}
                
                <SmallButton text={"Verify Code"} handlePressing={handleVerifyCode}/>
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