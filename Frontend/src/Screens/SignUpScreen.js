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
    const [nationality, setNationality] = useState('');
    const [username, setUsername] = useState('');
    const [agreeTerms, setAgreeTerms] = useState(false);
    const [loading, setLoading] = useState(false);

    const url = "http://192.168.1.4:8080";

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
            // const response = signUp(userData, setLoading);
            // console.log(response);
            navigation.navigate('VerificationCode');
        } else {
            Alert.alert('Error', 'Please agree to the Terms and Conditions before signing up.');
        }
    };

    return (
        <ScrollView contentContainerStyle={styles.scrollContainer}>
            <View style={styles.wholeForm}>

                <View style={styles.sectionContainer}>
                    <Text style={styles.sectionTitle}>Personal Information</Text>

                    <CustomTextInput placeholder={'First name'} title={'First name'} secure={false} onChangeText={(text) => setFirstName(text)} />
                    <CustomTextInput placeholder={'Last name'} title={'Last name'} secure={false} onChangeText={(lName) => setLastName(lName)} />
                    <CustomTextInput placeholder={'DD/MM/YYYY'} title={'Date of birth'} secure={false} onChangeText={(date) => setDate(date)} />

                </View>

                <View style={styles.sectionContainer}>
                    <Text style={styles.sectionTitle}>Contact Information</Text>

                    <CustomTextInput placeholder={'example@gmail.com'} title={'Email'} secure={false} onChangeText={(email) => setEmail(email)} />
                    <CustomTextInput placeholder={'Username'} title={'Username'} secure={false} onChangeText={(username) => setUsername(username)} />
                    <CustomTextInput placeholder={'Password (8 characters)'} title={'Password'} secure={false} onChangeText={(pass) => setPassword(pass)} />
                    <CustomTextInput placeholder={'Nationality'} title={'Nationality'} secure={false} onChangeText={(text) => setNationality(text)} />

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
