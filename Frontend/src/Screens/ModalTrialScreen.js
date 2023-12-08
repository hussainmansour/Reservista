import React, { useState } from 'react';
import { View, Text, Modal, StyleSheet, TouchableOpacity, Button } from 'react-native';

const ModalTrialScreen = () => {
  const [isModalVisible, setModalVisible] = useState(false);

  const toggleModal = () => {
    setModalVisible(!isModalVisible);
  };

  

  return (
    <View style={styles.container}>
      <Button title="Open Modal" onPress={toggleModal} />

      <Modal
        visible={isModalVisible}
        animationType="slide"
        transparent
        onRequestClose={toggleModal}
      >
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            <Text style={styles.modalText}>Hello, I'm a Modal!</Text>
            <Button title="Close" onPress={toggleModal} />
          </View>
        </View>
      </Modal>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  modalContent: {
    backgroundColor: 'white',
    padding: 20,
    borderRadius: 10,
    width: '80%',
  },
  modalText: {
    fontSize: 18,
    marginBottom: 10,
  },
});

export default ModalTrialScreen;
