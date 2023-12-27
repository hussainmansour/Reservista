// Create the TermsAndConditions screen
import React from 'react';
import { View, Text, ScrollView, StyleSheet } from 'react-native';
import colors from '../../Styles/Color';

// TermsAndConditionsScreen component
const TermsAndConditionsScreen = () => {
    return (
        <ScrollView style={styles.container}>
            <Text style={styles.title}>Terms and Conditions</Text>
            
            <Text style={styles.content}>
                Respectful Review Etiquette:
                {'\n\n'}
                We encourage guests to share their experiences through reviews. However, we kindly request that reviews remain constructive, respectful, and free from offensive language. Any review containing inappropriate content or violating community standards will be subject to moderation.
                {'\n\n'}

                Cancellation Policy Consideration:
                {'\n\n'}
                We understand that unforeseen circumstances may arise. If you need to cancel or modify your reservation, we kindly request that you do so in accordance with our cancellation policy. This allows us to better manage room availability for other guests. Please refer to the specific cancellation terms provided during the booking process.
                {'\n\n'}

                Privacy and Data Protection:
                {'\n\n'}
                Your privacy is important to us. By using our hotel reservation system, you agree to the collection and processing of your personal information for reservation and communication purposes. We assure you that your data will be handled with the utmost care and in accordance with applicable data protection laws. For more details, please refer to our Privacy Policy.
            </Text>
        </ScrollView>
    );
};

// Style
export default TermsAndConditionsScreen;

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: colors.MIDNIGHTBLUE,
        padding: 16,
    },
    title: {
        color: 'white',
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 20,
    },
    content: {
        color: 'white',
        fontSize: 16,
    },
});
