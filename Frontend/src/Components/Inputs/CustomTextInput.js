import React from 'react';
import { StyleSheet, Text, TextInput, View } from 'react-native';

const CustomTextInput = ({ placeholder, title, secure, onChangeText, errorMessage, type }) => {
  return (
    <View style={styles.inputContainer}>
      <Text style={styles.text}>{title}</Text>
      <TextInput
        placeholder={placeholder}
        secureTextEntry={secure}
        onChangeText={onChangeText}
        style={styles.textInput}
        keyboardType={type === null ? "default" : type}
      />
      {errorMessage ? (
        <Text style={styles.errorText}>{errorMessage}</Text>
      ) : null}
    </View>
  );
};

export default CustomTextInput;

const styles = StyleSheet.create({
  inputContainer: {
    marginBottom: 15,
    marginLeft: 20,
  },
  textInput: {
    backgroundColor: '#D9D9D9',
    borderRadius: 10,
    width: '90%',
    height: 50,
    paddingLeft: 10,
  },
  text: {
    color: 'white',
    fontWeight: 'bold',
    marginBottom: 5,
    paddingLeft: 10,
    fontSize: 17,
  },
  errorText: {
    color: 'red',
    fontSize: 14,
    marginTop: 5,
    marginLeft: 10,
  },
});
