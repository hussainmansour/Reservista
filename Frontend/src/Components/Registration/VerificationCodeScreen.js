import React, { useState, useEffect } from 'react';
import {
    View,
    Text,
    ScrollView,
    StyleSheet,
    Alert,
    ActivityIndicator,
    BackHandler,
} from 'react-native';
import CustomTextInput from '../Inputs/CustomTextInput';
import { refreshCode, verifyEmail } from '../../Utilities/API';
import CustomizedButton from '../General/Buttons/CustomizedButton';
import CountDown from 'react-native-countdown-component';

import colors from '../../Styles/Color';

const VerificationCodeScreen = ({ route, navigation }) => {
    const [verificationCode, setVerificationCode] = useState('');
    const [loading, setLoading] = useState(false);
    const [resendDisabled, setResendDisabled] = useState(true);
    const [verifyDisabled, setVerifyDisabled] = useState(false);
    const [counter, setCounter] = useState( 10 * 60);


    useEffect(() => {
        const handleBackPress = () => {
            // Prevent default behavior (going back)
            return true;
        };

        // Add event listener for hardware back button
        BackHandler.addEventListener('hardwareBackPress', handleBackPress);

        // Remove event listener on component unmount
        return () => {
            BackHandler.removeEventListener('hardwareBackPress', handleBackPress);
        };
    }, []); // Run only once on mount

    useEffect(() => {
        if (counter === 0) {
            setResendDisabled(false);
            setVerifyDisabled(true);
        }
    }, [counter]);

    const handleVerifyCode = async () => {
        const dto = {
            code: verificationCode,
            email: route.params.email,
        };

        try {
            const data = await verifyEmail(dto, setLoading);

            if (data.status === 200) {
                Alert.alert('Successful', 'Welcome to Reservista');
                navigation.navigate('Login');
            } else {
                console.log(data);
                Alert.alert('Error', 'Incorrect verification code. Please try again.');
            }
        } catch (error) {
            console.error(error);
            Alert.alert('Error', 'An unexpected error occurred.');
        } finally {
            // Disable the verify button after it's pressed
            setVerifyDisabled(true);
        }
    };

    const handleResendCode = async () => {
        const dto = {
            email: route.params.email,
        };

        try {
            await refreshCode(dto, setLoading);

            // Disable the resend button
            setResendDisabled(true);

            // Reset the counter
            setCounter(60 * 10);

            // Enable the verify button
            setVerifyDisabled(false);
        } catch (error) {
            console.error(error);
            Alert.alert('Error', 'Failed to resend verification code.');
        }
    };

    const handleCountdownFinish = () => {
        setVerifyDisabled(true);
        setResendDisabled(false);
    };

    return (
        <ScrollView contentContainerStyle={styles.container}>
            <CountDown
                until={counter} // duration in seconds
                size={40}
                onFinish={() => handleCountdownFinish()}
                digitStyle={{ backgroundColor: colors.SEABLUE, borderWidth: 1, borderColor: colors.MIDNIGHTBLUE, borderRadius: 25}}
                digitTxtStyle={{ color: colors.MIDNIGHTBLUE }}
                timeToShow={['M', 'S']}
                timeLabels={{ m: 'Minute', s: 'Second'}}
                style={{ marginBottom: 35}}
            />
            <View style={styles.form}>
                <Text style={styles.title}>Enter Verification Code</Text>
                <CustomTextInput
                    placeholder="OTP - 6 digits"
                    onChangeText={(text) => setVerificationCode(text)}
                    type="numeric"
                    errorMessage={'Code Will expire in 10 minutes'}
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
