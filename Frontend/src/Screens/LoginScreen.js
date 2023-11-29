import { StyleSheet, Text, TextInput, View, TouchableOpacity, Image } from 'react-native'
import React, {useState, useEffect} from 'react';
import CustomTextInput from '../Components/CustomTextInput';
import SmallButton from '../Components/SmallButton';
import * as WebBrowser from "expo-web-browser"
import * as Google from 'expo-auth-session/providers/google'
import * as React from 'react'



const LoginScreen = ({navigation}) => {
  const [username, usernameSet] = useState('');
  const [password , passwordSet] = useState('');
  const [userInfo, setUserInfo] = React.useState(null)
  const [request, response, promptAsync] = Google.useAuthRequest({
    androidClientId: "1031607216981-56eug3c6kc0m0opdoo880f46aimrakga.apps.googleusercontent.com",
    webClientId: "1031607216981-a811ens5029nos4vo5qgkjm99imtmlcg.apps.googleusercontent.com"
  })

  React.useEffect( () => {handleGoogleSignup();}, [response]);


  async function handleGoogleSignup(){
    if(response?. type === "success"){
      console.log(response.authentication.idToken)
      await getUserInfo(response.authentication.accessToken)
    }else{
      console.log("Failed")
    }
  }

  const getUserInfo = async (token) => {
    if(!token) return;
    try{
      const response = await fetch(
        "https://www.googleapis.com/userinfo/v2/me",
        {
          headers : { Authorization: `Bearer ${token}` }
        }
      );

      const user = await response.json();
      setUserInfo(user)
    }catch(error){
      console.log('Failed')
    }
  } 

  const handleLoginRequest = () => {
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
      
      <SmallButton text={"Login"} handlePressing={handleLoginRequest}/>
      

      <View style = {styles.signup}>
        <Text style = {{color: 'white'}} >Don’t have account?</Text>
        <TouchableOpacity onPress={() => navigation.navigate('Signup')}>
          <Text style = {styles.textSignup} >signup</Text>
        </TouchableOpacity>
      </View>
      

      <TouchableOpacity style = {styles.googleButtonContainer} onPress={() => promptAsync()}>
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