import { StyleSheet } from 'react-native';
import colors from './Color';

const styles = StyleSheet.create({
    scrollContainer: {
        flexGrow: 1,
    },
    wholeForm: {
        flex: 1,
        justifyContent: 'center',
        paddingHorizontal: 20,
    },
    sectionContainer: {
        marginBottom: 20,
    },
    sectionTitle: {
        color: 'white',
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 10,
    },
    termsContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        marginTop: 10,
    },
    checkbox: {
        marginRight: 8,
    },
    termsText: {
        color: 'white',
        fontSize: 17,
    },
    linkText: {
        color: colors.MIDNIGHTBLUE,
        textDecorationLine: 'underline',
        fontSize: 17,
    },
});

export default styles;
