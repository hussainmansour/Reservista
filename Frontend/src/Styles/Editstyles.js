import { StyleSheet } from "react-native";


const styles = StyleSheet.create({
    modalContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center'
    },
    modalContent: {
        backgroundColor: 'white',
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
        fontSize: 15,
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
        backgroundColor: '#4CAF50', // Green color
        paddingVertical: 10,
        paddingHorizontal: 20,
        borderRadius: 5,
        width: '30%',
        height: 50,
    },
    cancelButton: {
        backgroundColor: '#A9A9A9', // Gray color
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
});

export default styles;