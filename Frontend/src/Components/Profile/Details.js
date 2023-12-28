import React from 'react';
import { View, Text, Modal,StyleSheet } from 'react-native';
import CustomizedButton from '../General/Buttons/CustomizedButton';
import editStyles from '../../Styles/Editstyles';
import Color from '../../Styles/Color';

const Details = ({ isVisible, invoice, onClose }) => {
    return (
        <Modal
            visible={isVisible}
            animationType="slide"
            transparent={true}
        >
            <View style={styles.modalContainer}>
                <View style={styles.modalContent}>
                    <Text style={styles.modalTitle}>Details</Text>
                    <Text style={styles.invoiceText}>{invoice}</Text>
                    <CustomizedButton
                        text="Close"
                        onPress={onClose}
                        textStyle={editStyles.buttonText}
                        buttonStyle={editStyles.cancelButton}
                    />
                </View>
            </View>
        </Modal>
    );
};

const styles = StyleSheet.create({
    modalContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: Color.PALEBLUE,
    },
    modalContent: {
        backgroundColor: '#fff',
        borderRadius: 10,
        padding: 20,
        width: '80%',
        maxHeight: '80%',
    },
    modalTitle: {
        fontSize: 20,
        fontWeight: 'bold',
        marginBottom: 10,
        color: '#333333',
        textAlign: 'center',
    },
    invoiceText: {
        fontSize: 16,
        color: '#555555',
        lineHeight: 24,
        textAlign: 'justify',
    },
    buttonText: {
        color: '#FFFFFF',
        fontWeight: 'bold',
        textAlign: 'center',
    },
    button: {
        backgroundColor: Color.GREY,
        padding: 15,
        borderRadius: 10,
        marginTop: 10,
    },
});

export default Details;