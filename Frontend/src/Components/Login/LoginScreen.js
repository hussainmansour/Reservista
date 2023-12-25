import React, { useState , useContext } from 'react';
import {StyleSheet, Text, View, TouchableOpacity, Image, ActivityIndicator, Alert} from 'react-native';
import CustomTextInput from '../Inputs/CustomTextInput';
import {signIn} from '../../Utilities/API';
import {AuthContext} from '../../Store/authContext';
import CustomizedButton from '../General/Buttons/CustomizedButton';

const LoginScreen = ({ navigation }) => {
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
    let response = await signIn(userInfo, setLoading);
    if(response.status === 200){
      setIsAuthenticating(true);
      const token = response.data.token; // may need to change this
      console.log(token);
      authCtx.authenticate(token);
    }else{
      Alert.alert('Error', 'Please enter correct username or password');
      setIsAuthenticating(false);
    }
  };

  return (
    <View style={styles.wholeForm}>

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
      <CustomizedButton text={"Login"} onPress={handleLoginRequest} />

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
