import React, { useState, useContext } from 'react';
import { StyleSheet, Text, View, TouchableOpacity, Image, ActivityIndicator, Alert } from 'react-native';
import CustomTextInput from '../Inputs/CustomTextInput';
import { signIn } from '../../Utilities/API';
import { AuthContext } from '../../Store/authContext';
import CustomizedButton from '../General/Buttons/CustomizedButton';
import {LinearGradient} from 'expo-linear-gradient';
import styles from '../../Styles/Loginstyles';
import colors from '../../Styles/Color';

const LoginScreen = ({ navigation }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loading, setLoading] = useState(false);
    const [isAuthenticating, setIsAuthenticating] = useState(false);
    const authCtx = useContext(AuthContext);

    const handleLoginRequest = async () => {
        const userInfo = {
            userNameOrEmail: username,
            password: password,
        };

        let response = await signIn(userInfo, setLoading);

        if (response.status === 200) {
            setIsAuthenticating(true);
            const token = response.data.token; // may need to change this
            console.log(token);
            authCtx.authenticate(token);
        } else {
            Alert.alert('Error', 'Please enter correct username or password');
            setIsAuthenticating(false);
        }
    };

    return (
        <LinearGradient
            colors={[colors.MIDNIGHTBLUE, colors.SEABLUE]} // Define your gradient colors
            style={styles.wholeForm}
        >
            <View style={styles.wholeFormContent}>
                <Image source={require('../../Data/Logo.png')} style={{ alignSelf: 'center' }} />

                <CustomTextInput
                    placeholder={'Username or Email'}
                    title={'Username or Email'}
                    secure={false}
                    onChangeText={(text) => setUsername(text)}
                />

                <CustomTextInput
                    placeholder={'Password'}
                    title={'Password'}
                    secure={true}
                    onChangeText={(text) => setPassword(text)}
                    keyboardType="visible-password"
                />

                {loading && <ActivityIndicator size="large" color="#0000ff" />}
                <CustomizedButton
                    text={"Login"}
                    onPress={handleLoginRequest}
                    buttonStyle={styles.loginButton} // Add custom style for the login button
                    textStyle={styles.loginButtonText} // Add custom style for the login button text
                />

                <View style={styles.signupView}>
                    <Text style={styles.signup}>Don’t have an account?</Text>
                    <TouchableOpacity onPress={() => navigation.navigate('Sign up')}>
                        <Text style={styles.textSignup}>signup</Text>
                    </TouchableOpacity>
                </View>
            </View>
        </LinearGradient>
    );
};

export default LoginScreen;
