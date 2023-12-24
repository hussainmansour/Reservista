import {useContext} from "react";
import {AuthContext} from "../Store/authContext";
import Home from "../Components/Home/Home";
import IconButton from "../Components/IconButton";
import Welcome from "../Screens/Welcome";
import {createNativeStackNavigator} from "@react-navigation/native-stack";
import {Colors} from "react-native/Libraries/NewAppScreen";
import {StyleSheet} from "react-native";
import Profile from "../Components/Profile/Profile";
import ProfileStack from "./ProfileStack";

import CartScreen from "../Components/Reservation/CartScreen";

import SearchAndFilter from "../Components/SearchAndFilter/SearchAndFilter";
import SearchOptions from "../Components/Home/SearchOptions";
import SearchAndFilterHeader from "../Components/SearchAndFilter/SearchAndFilterHeader";
import SortAndFilterSelector from "../Components/SearchAndFilter/SortAndFilterSelector";
import HotelView from "../Components/Hotel/HotelView";


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
            <Stack.Screen
            name="Profile"
            component={ProfileStack}
            options={{headerShown:false}}
            ></Stack.Screen>

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
            name="Hotel"
            component={HotelView}
            options={{headerShown: false}}
            />
            <Stack.Screen
                name="CartScreen"
                component={CartScreen}
                options={{headerShown: false}}
            />
            {/* <Stack.Screen
                name="SortAndFilterSelector"
                component={SortAndFilterSelector}
                options={{headerShown: false}}
                component={ProfileStack}
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
            /> */}
        </Stack.Navigator>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
});
