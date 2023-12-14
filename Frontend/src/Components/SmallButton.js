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
        
        marginTop: 30,
        width: 118,
        height: 43,
        alignItems: 'center',
        justifyContent: 'center',
        borderRadius: 10,
        backgroundColor: '#728FF3',
        alignSelf: 'center',
    },
    Text:{
        fontSize: 20,
        color: 'white'
    },
})