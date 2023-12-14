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
                component={CartScreen}
                initialParams={{
                    price: 40,
                    title: "title",
                    count: 4,
                    roomDescriptionId: "0002d331-89d7-4f19-b6bf-8cf553b767c5",
                    hotelID:"001cc902-bea3-4381-86af-4064e3b90fc8",
                    refundable:true,
                    fullyRefundableRate: 15,
                    checkIn: "2024-03-01T00:00:00Z",
                    checkOut: "2024-03-02T00:00:00Z",
                    foodOptions : { breakfastPrice: 21, lunchPrice: 57, dinnerPrice: 35 }
                }}
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
