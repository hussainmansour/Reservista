import {StatusBar} from 'expo-status-bar';
import {StyleSheet, Text, View} from 'react-native';
import AuthContextProvider, {AuthContext} from './src/Store/authContext';
import {useContext, useEffect, useState} from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";
import {Navigation} from "./src/Navigations/Navigation";


function Root() {
    const [isTryingLogin, setIsTryingLogin] = useState(true);

    const authCtx = useContext(AuthContext);

    useEffect(() => {
        async function fetchToken() {
            const storedToken = await AsyncStorage.getItem('token');

            if (storedToken)
                authCtx.authenticate(storedToken);
            setIsTryingLogin(false);
        }

        fetchToken();
    }, []);

    // if (isTryingLogin) {
    //     return <AppLoading />;
    // }

    return <Navigation/>;
}

export default function App() {

    return (
        <>
            <StatusBar style="light"/>
            <AuthContextProvider>
                <Root/>
            </AuthContextProvider>
        </>
    );
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
});
