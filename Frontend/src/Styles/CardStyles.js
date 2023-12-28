
import { StyleSheet } from "react-native";
import Color from "./Color";


// Styles
const cardStyles = StyleSheet.create({
    CardContainer: {
        flexDirection: 'row',
        margin: 10,
        borderRadius: 15,
        overflow: 'hidden',
        backgroundColor: '#ffffff',
        elevation: 5,
        shadowColor: '#000',
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
    },
    roomImagesContainer: {
        height: 150,
    },
    roomImage: {
        width: 150,
        height: 150,
        borderRadius: 15,
    },
    InfoContainer: {
        padding: 15,
        flex: 1,
    },
    Title: {
        fontSize: 18,
        fontWeight: 'bold',
        marginBottom: 5,
        color: '#333333',
    },
    Button: {
        backgroundColor: Color.SEABLUE,
        padding: 15,
        borderRadius: 10,
        marginTop: 10,
        elevation: 5,
        shadowColor: '#000',
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
        width: '40%',
        height: 'auto',
        alignSelf: 'auto'
    },
    ButtonText: {
        fontSize: 15,
        color: '#FFFFFF',
        fontWeight: 'bold',
        textAlign: 'center',
    },
});
export default cardStyles;
