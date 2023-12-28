import React, { useState, useEffect } from 'react';
import { View, Text, ScrollView, StyleSheet, Alert, ActivityIndicator } from 'react-native';
import CustomTextInput from '../Inputs/CustomTextInput';
import CustomizedButton from '../General/Buttons/CustomizedButton';
import colors from '../../Styles/Color';
import { OTPAPI } from '../../Utilities/New/APIs/OTPAPI';

const VerificationCodeScreen = ({ route, navigation }) => {
    const counterTimeInSeconds = 10 * 60;
    const [verificationCode, setVerificationCode] = useState('');
    const [loading, setLoading] = useState(false);
    const [resendDisabled, setResendDisabled] = useState(true);
    const [verifyDisabled, setVerifyDisabled] = useState(false);
    const [counter, setCounter] = useState(counterTimeInSeconds);

    useEffect(() => {
        const intervalId = setInterval(() => {
            setCounter((prevCounter) => Math.max(0, prevCounter - 1));

            if (counter === 1) {
                setVerifyDisabled(true);
                setResendDisabled(false);
            }
        }, 1000);

        return () => clearInterval(intervalId);
    }, [counter]);


    const handleVerifyCode = async () => {
        const dto = {
            code: verificationCode,
            email: route.params.email,
        };


        const response = await OTPAPI.verifyGmailAccount(dto, (response) => {
            const responseBody = response.data;
            if (responseBody.data !== undefined) {
                Alert.alert('Error', responseBody.data);
            } else {
                console.log(responseBody);
            }
        }, setLoading);

        if (response !== undefined ){
            Alert.alert('Success', 'Your account has been verified. Welcome to Reservista!');
            navigation.navigate('Login');
        }

    };

    const handleResendCode = async () => {


        const dto = { email: route.params.email };

        const response = await OTPAPI.refreshOTP(dto, (response) => {
            const responseBody = response.data;
            if (responseBody.data !== undefined) {
                Alert.alert('Error', responseBody.data);
            } else {
                console.log(responseBody);
            }
        }, setLoading);

        if (response !== undefined ){
            setResendDisabled(true);
            setCounter(counterTimeInSeconds);
            setVerifyDisabled(false);
            Alert.alert('Success Resending OTP', 'Verification code has been sent to your email again.');
        }

    };

    return (
        <ScrollView contentContainerStyle={styles.container}>
            <View style={styles.form}>
                <Text style={styles.title}>Enter Verification Code</Text>
                <CustomTextInput
                    placeholder="OTP - 6 digits"
                    onChangeText={(text) => setVerificationCode(text)}
                    type="numeric"
                    errorMessage={`Code will expire in ${String(Math.floor(counter / 60)).padStart(2, '0')}:${String(counter % 60).padStart(2, '0')}`}
                />

                {loading && <ActivityIndicator size="large" color="#0000ff" />}

                <CustomizedButton
                    text="Verify Code"
                    onPress={handleVerifyCode}
                    buttonStyle={styles.verifyButton}
                    textStyle={styles.buttonText}
                    disabled={verifyDisabled}
                />
                <CustomizedButton
                    text="Resend Code"
                    onPress={handleResendCode}
                    buttonStyle={styles.resendButton}
                    textStyle={styles.buttonText}
                    disabled={resendDisabled}
                />
            </View>
        </ScrollView>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: colors.PALEBLUE,
        justifyContent: 'center',
        paddingHorizontal: 20,
    },
    form: {
        backgroundColor: colors.SEABLUE,
        borderRadius: 10,
        padding: 20,
    },
    title: {
        fontSize: 18,
        color: colors.MIDNIGHTBLUE,
        marginBottom: 10,
        fontWeight: 'bold',
    },
    verifyButton: {
        backgroundColor: colors.ORANGE,
        marginTop: 10,
        height: 50,
        borderRadius: 10,
        alignItems: 'center',
        justifyContent: 'center',
        marginBottom: 20,
    },
    buttonText: {
        fontSize: 20,
        color: 'white',
    },
    resendButton: {
        backgroundColor: colors.MIDNIGHTBLUE,
        alignSelf: 'center',
        marginTop: 20,
        width: '50%',
        height: 50,
        borderRadius: 10,
        alignItems: 'center',
        justifyContent: 'center',
        marginBottom: 20,
    },
    resendText: {
        fontSize: 16,
        color: 'white',
    },
});

export default VerificationCodeScreen;
