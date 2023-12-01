import { StyleSheet, Text, View, TouchableOpacity } from 'react-native'
import { useNavigation } from '@react-navigation/native';
import React from 'react'

const SmallButton = ({text, handlePressing}) => {
    const navigation = useNavigation();
  return (
    <TouchableOpacity style = {styles.ButtonContainer} onPress={handlePressing}> 
          <Text style = {styles.Text} >{text}</Text>
    </TouchableOpacity>
  )
}

export default SmallButton

const styles = StyleSheet.create({
    ButtonContainer:{
        backgroundColor: '#728FF3',
        alignSelf: 'center',
        marginTop: 30,
        width: 118,
        height: 43,
        borderRadius: 10,
        alignItems: 'center',
        justifyContent: 'center',
    },
    Text:{
        fontSize: 20,
        color: 'white'
    },
})