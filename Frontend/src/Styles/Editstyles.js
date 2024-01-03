import { StyleSheet } from "react-native";
import Color from "./Color";


const editStyles = StyleSheet.create({
    modalContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center'
    },
    modalContent: {
        backgroundColor: Color.PALEBLUE,
        padding: 20,
        flex: 1,
        width: '100%',
    },
    modalTitle: {
        marginBottom: 10,
        textAlign: 'center',
        fontFamily: "Roboto",
        fontSize: 22,
        fontWeight: "400",
        letterSpacing: 0,
        lineHeight: 28,
    },
    fieldContainer: {
        marginBottom: 30,
        marginLeft: 20,
        borderRadius: 10
    },
    fieldLabel: {
        color: 'black',
        fontWeight: 'bold',
        marginBottom: 5,
        paddingLeft: 10,
        fontSize: 17,
    },
    input: {
        backgroundColor: '#D9D9D9',
        borderRadius: 10,
        width: '90%',
        height: 55,
        paddingLeft: 10,
    },
    picker: {
        height: 40,
        borderColor: 'gray',
        borderWidth: 1,
        backgroundColor: '#D9D9D9',
    },
    pickerItem: {
        height: 20,
        fontSize: 20,
    },
    errorInput: {
        borderColor: 'red',
    },
    errorText: {
        color: 'red',
        fontSize: 12,
        marginLeft: 10,
    },
    buttonContainer: {
        flexDirection: 'row',
        justifyContent: 'space-around',
        marginTop: 20,
        paddingBottom: 20
    },
    saveButton: {
        backgroundColor: Color.SEABLUE, // Green color
        paddingVertical: 10,
        paddingHorizontal: 20,
        borderRadius: 5,
        width: '30%',
        height: 50,
    },
    cancelButton: {
        backgroundColor: Color.GREY, // Gray color
        paddingVertical: 10,
        paddingHorizontal: 20,
        borderRadius: 5,
        width: '30%',
        height: 50,
    },
    buttonText: {
        color: 'white',
        fontSize: 16,
        fontWeight: 'bold',
        textAlign: 'center',
    },
    invoiceText: {
        fontSize: 16,
        color: '#555555',
        lineHeight: 24,
        textAlign: 'justify',
    },
});

export default editStyles;