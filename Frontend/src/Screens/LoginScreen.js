import React, { useState } from 'react';
import { StyleSheet, Text, View, TouchableOpacity, Image } from 'react-native';
import CustomTextInput from '../Components/CustomTextInput';
import SmallButton from '../Components/SmallButton';

const LoginScreen = ({ navigation }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLoginRequest = () => {
    const userData = {
      username,
      password
    };

    console.log(userData);
  };

  return (
    <View style={styles.wholeForm}>

      <Image source={require('../Data/Logo.png')} style={{ alignSelf: 'center' }} />

      <CustomTextInput placeholder={'Username'} title={'Username'} secure={false} onChangeText={(text) => setUsername(text)} />
      <CustomTextInput placeholder={'Password'} title={'Password'} secure={true} onChangeText={(text) => setPassword(text)} />

      <SmallButton text={"Login"} handlePressing={handleLoginRequest} />

      <View style={styles.signup}>
        <Text style={{ color: 'white' }}>Don’t have an account?</Text>
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
