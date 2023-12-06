import {useContext} from "react";
import {AuthContext} from "../Store/authContext";
import {NavigationContainer} from "@react-navigation/native";
import AuthStack from "./AuthStack";
import AuthenticatedStack from "./AuthenticatedStack";

export function Navigation() {
    const authCtx = useContext(AuthContext);

    return (
        <NavigationContainer>
            {!authCtx.isAuthenticated && <AuthStack/>}
            {authCtx.isAuthenticated && <AuthenticatedStack/>}
        </NavigationContainer>
    );
}
