import React, { useState } from 'react';
import Checkbox from 'expo-checkbox';
import {
    View,
    Text,
    TouchableOpacity,
    ScrollView,
    StyleSheet,
    Alert, ActivityIndicator,
} from 'react-native';
import CustomTextInput from '../Components/CustomTextInput';
import SmallButton from '../Components/SmallButton';
import {refreshCode, verifyEmail} from '../Utilities/API'


const VerificationCodeScreen = ({ route, navigation }) => {
    const [verificationCode, setVerificationCode] = useState('');
    const [loading, setLoading] = useState(false);

    const handleVerifyCode = async () => {
        const dto = {
            code: verificationCode,
            email: route.params.email
        }
        let data = await verifyEmail(dto, setLoading)

        if (data.status === 200) {
             console.log('Email successfully verified!');
             navigation.navigate('Login');
        } else {
            console.log(data);
            Alert.alert('Error', 'Incorrect verification code. Please try again.');
        }
    };
    
    const handleResendCode = async () => {
        const dto = {
            email: route.params.email
        }

        await refreshCode(dto, setLoading);
    };
    
    return (
        <ScrollView contentContainerStyle={styles.scrollContainer}>
            <View style={styles.wholeForm}>
                <CustomTextInput
                    placeholder={'123456'}
                    title={'Enter Verification Code'}
                    secure = {false}
                    onChangeText={(text) => setVerificationCode(text)}
                    keyboardType= 'numeric'
                />
                
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