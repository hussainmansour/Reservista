import {createNativeStackNavigator} from "@react-navigation/native-stack";
import AdminHome from "../Components/Admin/AdminHome";
import IconButton from "../Components/General/Buttons/IconButton";
import {useContext} from "react";
import {AuthContext} from "../Store/authContext";
import Color from "../Styles/Color";

const Stack = createNativeStackNavigator();
export default function AuthenticatedAdminStack() {
    const authCtx = useContext(AuthContext);
    return (

            <Stack.Navigator initialRouteName="AdminHome">
                <Stack.Screen
                    name = "AdminHome"
                    component={AdminHome}
                    options={{
                    headerStyle:{
                        backgroundColor: Color.SEABLUE,
                    },
                    headerTitleStyle: {
                        fontWeight: 'bold',
                        fontSize: 20,
                        color: Color.MIDNIGHTBLUE,
                    },
                    headerRight: () => (
                        <IconButton
                            icon="exit"
                            color="white"
                            size={24}
                            onPress={authCtx.logout}
                        />
                    ),
                }}  />
            </Stack.Navigator>

    );

}