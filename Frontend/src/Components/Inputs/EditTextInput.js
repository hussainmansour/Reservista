import React from 'react';
import { StyleSheet, Text, TextInput, View } from 'react-native';

const EditTextInput = ({ placeholder, title, secure, onChangeText, errorMessage, type , onBlur, Value}) => {
  return (
    <View style={styles.inputContainer}>
      <Text style={styles.text}>{title}</Text>
      <TextInput
        onChangeText={onChangeText}
        onBlur={onBlur}
        style={styles.textInput}
        keyboardType={type === null ? "default" : type}
        value={Value}

      />
      {errorMessage ? (
        <Text style={styles.errorText}>{errorMessage}</Text>
      ) : null}
    </View>
  );
};

export default EditTextInput;

const styles = StyleSheet.create({
  inputContainer: {
    marginBottom: 15,
    marginLeft: 20,
  },
  textInput: {
    backgroundColor: '#D9D9D9',
    borderRadius: 10,
    width: '90%',
    height: 55,
    paddingLeft: 10,
  },
  text: {
    color: 'black',
    fontWeight: 'bold',
    marginBottom: 5,
    paddingLeft: 10,
    fontSize: 15,
  },
  errorText: {
    color: 'red',
    fontSize: 14,
    marginTop: 5,
    marginLeft: 10,
  },
});
