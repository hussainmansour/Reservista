import {StyleSheet, View} from "react-native";
import {StatusBar} from "expo-status-bar";
import LoginScreen from "../Screens/LoginScreen";
import SignupScreen from "../Screens/SignUpScreen";
import TermsAndConditionsScreen from "../Screens/TermsAndConditionsScreen";
import VerificationCodeScreen from "../Screens/VerificationCodeScreen";
import {createNativeStackNavigator} from "@react-navigation/native-stack";

const Stack = createNativeStackNavigator();
export default function AuthStack() {
    return (
        <View style={styles.container}>
            <StatusBar
                backgroundColor="white"
                barStyle="light-content"
                translucent={true}
            />
            <Stack.Navigator>
                <Stack.Screen name = "Login" component={LoginScreen} options={{ headerShown: false }} />
                <Stack.Screen name = 'Signup' component = {SignupScreen}/>
                <Stack.Screen name = 'TermsAndConditions' component={TermsAndConditionsScreen} />
                <Stack.Screen name = 'VerificationCode' component={VerificationCodeScreen} />
            </Stack.Navigator>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
});
