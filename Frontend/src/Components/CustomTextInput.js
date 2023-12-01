import { StyleSheet, Text, TextInput, View, TouchableOpacity, Image } from 'react-native'
import React from 'react'

const CustomTextInput = ({placeholder, title, secure, onChangeText}) => {
  return (
    <View style = {styles.inputContainer}>
        <Text style = {styles.text} > {title} </Text>
        <TextInput 
        placeholder = {placeholder}  
        secureTextEntry = {secure} 
        onChangeText={onChangeText}
        style = {styles.textInput}></TextInput>
    </View>
  )
}

export default CustomTextInput

const styles = StyleSheet.create({
    inputContainer:{
        marginBottom: 15,
        marginLeft: 20 ,
    },
    textInput:{
        backgroundColor: '#D9D9D9',
        borderRadius: 10,
        width: '90%',
        height: 46,
        paddingLeft: 10  
    },
    text:{
      color: 'white',
      fontWeight: 'bold',
      marginBottom: 5,
      paddingLeft: 10,
      fontSize: 20
    }

})