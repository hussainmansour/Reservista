// Create the TermsAndConditions screen
import {
    View,
    Text,
    TextInput,
    TouchableOpacity,
    ScrollView,
    StyleSheet,
    Alert,
} from 'react-native';

// TermsAndConditionsScreen component
const TermsAndConditionsScreen = () => {
    return (
        <View style={styles.container}>
            <Text style={styles.title}>Terms and Conditions</Text>
            {/* Add your terms and conditions content here */}
        </View>
    );
};

// Style
export default TermsAndConditionsScreen;

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#131141',
    },
    title: {
        color: 'white',
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 20,
    },
});