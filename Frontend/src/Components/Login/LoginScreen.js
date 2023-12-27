import React, {useState, useContext} from 'react';
import {StyleSheet, Text, View, TouchableOpacity, Image, ActivityIndicator, Alert} from 'react-native';
import CustomTextInput from '../Inputs/CustomTextInput';
import {signIn} from '../../Utilities/API';
import {AuthContext} from '../../Store/authContext';
import CustomizedButton from '../General/Buttons/CustomizedButton';
import {AuthenticationAPI} from "../../Utilities/New/APIs/AuthenticationAPI";

const LoginScreen = ({navigation}) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loading, setLoading] = useState(false);
    const [isAuthenticating, setIsAuthenticating] = useState(false);
    const authCtx = useContext(AuthContext);
    const handleLoginRequest = async () => {
        const userInfo = {
            userNameOrEmail: username,
            password: password
        }

        let response = await AuthenticationAPI.login(userInfo, (response) => {

            // in case of an expected error this should be the errorDTO
            const responseBody = response.data;


            if (responseBody.data !== undefined) {
                // check for error code

                Alert.alert('Error', responseBody.data);
                setIsAuthenticating(false);
            } else {
                // if it doesn't have the data attribute then it's not the errorDTO
                // so, it's an unhandled exception
                console.log(responseBody)
            }

        }, setLoading);

        // success
        if (response !== undefined) {
            setIsAuthenticating(true);
            const token = response;
            console.log(token);
            authCtx.authenticate(token);
        }
    };

    return (
        <View style={styles.wholeForm}>

            <Image source={require('../../Data/Logo.png')} style={{alignSelf: 'center'}}/>

            <CustomTextInput
                placeholder={'Username'}
                title={'Username'}
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

            {loading && <ActivityIndicator size="large" color="#0000ff"/>}
            <CustomizedButton text={"Login"} onPress={handleLoginRequest}/>

            <View style={styles.signup}>
                <Text style={{color: 'white'}}>Don’t have an account?</Text>
                <TouchableOpacity onPress={() => navigation.navigate('Signup')}>
                    <Text style={styles.textSignup}>signup</Text>
                </TouchableOpacity>
            </View>

        </View>
    );
};

export default LoginScreen;

const styles = StyleSheet.create({
    wholeForm: {
        backgroundColor: '#131141',
        flex: 1,
        justifyContent: 'center',
    },
    signup: {
        flexDirection: 'row',
        alignSelf: 'center',
        marginBottom: 20,
        marginTop: 30,
    },
    textSignup: {
        color: 'white',
        fontWeight: 'bold',
        marginBottom: 5,
        paddingLeft: 10,
        textDecorationLine: 'underline',
        fontSize: 15
    },
});
