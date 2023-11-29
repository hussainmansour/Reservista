import { StyleSheet, Text, TextInput, View, TouchableOpacity, Image } from 'react-native'
import React, {useState, useEffect} from 'react';
import CustomTextInput from '../Components/CustomTextInput';
import SmallButton from '../Components/SmallButton';


const LoginScreen = ({navigation}) => {
  const [username, usernameSet] = useState('');
  const [password , passwordSet] = useState('');

  const handle = () => {
    const userData = {
      username,
      password
    };

    console.log(userData)
  }

  return (
    <View style = {styles.wholeForm}>

      <Image source = {require('../Data/Logo.png')} style = {{alignSelf: 'center'}}/>
      
      <CustomTextInput placeholder={'Username'} title={'Username'} secure = {false} onChangeText={(text) => usernameSet(text)}/>
      <CustomTextInput placeholder={'Password'} title={'Password'} secure = {true} onChangeText={(text) => passwordSet(text)}/>      
      
      <SmallButton text={"Login"} handlePressing={handle}/>
      

      <View style = {styles.signup}>
        <Text style = {{color: 'white'}} >Don’t have account?</Text>
        <TouchableOpacity onPress={() => navigation.navigate('Signup')}>
          <Text style = {styles.textSignup} >signup</Text>
        </TouchableOpacity>
      </View>
      

      <TouchableOpacity style = {styles.googleButtonContainer}>
        <Text style = {styles.googleText} >Continue with Google</Text>
        <Image source = {require('../Data/Google.png')} style = {styles.img}/>
      </TouchableOpacity>
      
    </View>
  )
}

export default LoginScreen

const styles = StyleSheet.create({
  wholeForm: {
    backgroundColor: '#131141',
    flex: 1,
    justifyContent: 'center',
  },
  signup:{
    flexDirection: 'row',
    alignSelf:'center',
    marginBottom: 20,
    marginTop: 30,
    
  },
  textSignup:{
    color: 'white',
    fontWeight: 'bold',
    marginBottom: 5,
    paddingLeft: 10,
    textDecorationLine: 'underline',
    fontSize: 15
  },
  googleButtonContainer:{
    backgroundColor: '#728FF3',
    alignSelf: 'center',
    marginTop: 30,
    width: 240,
    height: 43,
    borderRadius: 10,
    justifyContent: 'space-evenly',
    flexDirection: 'row', 
    paddingTop: 10
  },
  googleText:{
    color: 'white',
    fontSize: 15,
    paddingLeft: 25
  },
  img:{
    width:28,
    height: 25,
  }
})