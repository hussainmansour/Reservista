import {useContext} from "react";
import {AuthContext} from "../Store/authContext";
import {NavigationContainer} from "@react-navigation/native";
import AuthStack from "./AuthStack";
import AuthenticatedStack from "./AuthenticatedStack";
import AuthenticatedAdminStack from"./AuthenticatedAdminStack"

export function Navigation() {
    const authCtx = useContext(AuthContext);
    console.log(authCtx)
    return (
        <NavigationContainer>
            {!authCtx.isAuthenticated && <AuthStack/>}
            {authCtx.isAuthenticated && authCtx.isAdmin && <AuthenticatedAdminStack/>}
            {authCtx.isAuthenticated && !authCtx.isAdmin && <AuthenticatedStack/>}
        </NavigationContainer>
    );
}
