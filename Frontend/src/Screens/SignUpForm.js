import { StyleSheet, Text, TextInput, View, TouchableOpacity, Button, Image } from 'react-native'
import React, {useState, useEffect} from 'react';
import { useNavigation } from '@react-navigation/native';
import axios from 'axios';
import CustomTextInput from '../Components/CustomTextInput';
import SmallButton from '../Components/SmallButton';




const SignupScreen = () => {
  const [firstName, firstNameSet] = useState('');
  const [lastName , lastNameSet ] = useState('');
  const [email, emailSet] = useState('');
  const [password, passwordSet] = useState('');
  const [date, dateSet] = useState('');
  const url = "http://192.168.1.4:8080"

  const navigation = useNavigation();

  const handleNavigation = async () => {
    const userData = {
      firstName,
      lastName,
      email,
      password,
      date
    };

    // const response = await axios.post(url+'/register', userData);
    console.log(userData)
    console.log("DONE")

    // if (response.data === "YES") {
    //   navigation.navigate('Login');
    // } else {
    //   console.log('Navigation not allowed. Handle error or show a message.');
    // }

  };

  return (
    <View style = {styles.wholeForm}>

      <CustomTextInput placeholder={"First name"} title={"First name"} secure={false} onChangeText={(text) => firstNameSet(text)}/>
      <CustomTextInput placeholder={"Last name"} title={"Last name"} secure={false} onChangeText={(lName) => lastNameSet(lName)}/>
      <CustomTextInput placeholder={"example@gmail.com"} title={"Email"} secure={false} onChangeText={(email) => emailSet(email)}/>
      <CustomTextInput placeholder={"Password (8-charachters)"} title={"Password"} secure={true} onChangeText={(pass) => passwordSet(pass)}/>
      <CustomTextInput placeholder={"DD/MM/YYYY"} title={"Date of birth"} secure={false} onChangeText={(date) => dateSet(date)}/>

      <SmallButton text={"Sign Up"} navComponentName={"Login"} handlePressing = {handleNavigation}/>
    </View>
  )
}

export default SignupScreen

const styles = StyleSheet.create({
    wholeForm: {
        backgroundColor: '#131141',
        flex: 1,
        justifyContent: 'center',
    },
})