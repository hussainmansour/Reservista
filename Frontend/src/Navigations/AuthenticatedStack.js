import {useContext} from "react";
import {AuthContext} from "../Store/authContext";
import Home from "../Screens/Home";
import IconButton from "../Components/IconButton";
import Welcome from "../Screens/Welcome";
import {createNativeStackNavigator} from "@react-navigation/native-stack";
import {Colors} from "react-native/Libraries/NewAppScreen";
import {StyleSheet} from "react-native";
import CartScreen from "../Screens/CartScreen";

const Stack = createNativeStackNavigator();

export default function AuthenticatedStack() {
    const authCtx = useContext(AuthContext);
    return (
        <Stack.Navigator
            screenOptions={{
                headerStyle: {backgroundColor: Colors.primary500},
                headerTintColor: 'white',
                contentStyle: {backgroundColor: Colors.primary100},
            }}
        >
            
            <Stack.Screen
                name="Home"
                component={Home}
                options={{
                    headerRight: ({tintColor}) => (
                        <IconButton
                            icon="exit"
                            color={tintColor}
                            size={24}
                            onPress={authCtx.logout}
                        />
                    ),
                }}
            />
        </Stack.Navigator>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
});
