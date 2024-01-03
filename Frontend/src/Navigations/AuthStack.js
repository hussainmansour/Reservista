import {StyleSheet, View} from "react-native";
import {StatusBar} from "expo-status-bar";
import LoginScreen from "../Components/Login/LoginScreen";
import SignupScreen from "../Components/Registration/SignUpScreen";
import TermsAndConditionsScreen from "../Components/Registration/TermsAndConditionsScreen";
import VerificationCodeScreen from "../Components/Registration/VerificationCodeScreen";
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
                {/* <Stack.Screen name = "CartScreen" component={CartScreen} options={{ headerShown: false }} /> */}
                <Stack.Screen name = "Login" component={LoginScreen} options={{ headerShown: false }} />
                <Stack.Screen name ="Sign up" component = {SignupScreen}/>
                <Stack.Screen name = 'Terms And Conditions' component={TermsAndConditionsScreen} />
                <Stack.Screen name = 'Verification Code' component={VerificationCodeScreen} options={{ headerShown: false }} />
            </Stack.Navigator>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
});
