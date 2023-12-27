import { StyleSheet } from "react-native";
import colors from "./Color";

const styles = StyleSheet.create({
    wholeForm: {
        flex: 1,
    },
    wholeFormContent: {
        backgroundColor: 'transparent',
        flex: 1,
        justifyContent: 'center',
        paddingHorizontal: 20,
    },
    signupView: {
        flexDirection: 'row',
        alignSelf: 'center',
        marginBottom: 100,
        marginTop: 20,
    },
    signup: {
        color: 'white',
        fontSize: 17,
    },
    textSignup: {
        color: 'white',
        fontWeight: 'bold',
        marginBottom: 5,
        paddingLeft: 10,
        textDecorationLine: 'underline',
        fontSize: 17,
    },
    loginButton: {
        alignSelf: 'center',
        marginTop: 20,
        width: '35%',
        height: 50,
        borderRadius: 10,
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: colors.ORANGE,
    },
    loginButtonText: {
        fontSize: 20,
        color: 'white',
    },
});

export default styles;