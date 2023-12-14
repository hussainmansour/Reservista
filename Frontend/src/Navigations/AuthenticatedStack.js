import {useContext} from "react";
import {AuthContext} from "../Store/authContext";
import Home from "../Screens/Home";
import IconButton from "../Components/IconButton";
import Welcome from "../Screens/Welcome";
import {createNativeStackNavigator} from "@react-navigation/native-stack";
import {Colors} from "react-native/Libraries/NewAppScreen";
import {StyleSheet} from "react-native";
import SearchAndFilter from "../Screens/SearchAndFilter";
import SearchOptions from "../Components/SearchOptions";
import SearchAndFilterHeader from "../Components/SearchAndFilterHeader";
import SortAndFilterSelector from "../Components/SortAndFilterSelector";

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
                options={{headerShown: false}}
                // options={{
                //     headerRight: ({tintColor}) => (
                //         <IconButton
                //             icon="exit"
                //             color={tintColor}
                //             size={24}
                //             onPress={authCtx.logout}
                //         />
                //     ),
                // }}
            />
            <Stack.Screen
                name="SearchAndFilter"
                component={SearchAndFilter}
                options={{headerShown: false}}
            />
            <Stack.Screen
                name="SearchOptions"
                component={SearchOptions}
                options={{headerShown: false}}
            />
            <Stack.Screen
                name="SearchAndFilterHeader"
                component={SearchAndFilterHeader}
                options={{headerShown: false}}
            />
            <Stack.Screen
                name="SortAndFilterSelector"
                component={SortAndFilterSelector}
                options={{headerShown: false}}
            />
        </Stack.Navigator>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
});
