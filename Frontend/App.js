import {StatusBar} from 'expo-status-bar';
import {StyleSheet, Text, View} from 'react-native';
import AuthContextProvider, {AuthContext} from './src/Store/authContext';
import {useContext, useEffect, useState} from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";
import {Navigation} from "./src/Navigations/Navigation";
import {GluestackUIProvider, Box} from "@gluestack-ui/themed"
import {config} from "@gluestack-ui/config"
import {AutocompleteDropdownContextProvider} from "react-native-autocomplete-dropdown";
import * as eva from '@eva-design/eva';
import {ApplicationProvider} from '@ui-kitten/components';
import SearchCriteriaContextProvider from "./src/Store/searchCriteriaContext";
import SearchOptionsContextProvider from "./src/Store/SearchOptionsContext";

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
        <ApplicationProvider {...eva} theme={eva.light}>
            <AutocompleteDropdownContextProvider>
                <GluestackUIProvider config={config}>
                    <StatusBar style="light"/>
                    <AuthContextProvider>
                        <SearchOptionsContextProvider>
                            <SearchCriteriaContextProvider>
                                <Root/>
                            </SearchCriteriaContextProvider>
                        </SearchOptionsContextProvider>
                    </AuthContextProvider>
                </GluestackUIProvider>
            </AutocompleteDropdownContextProvider>
        </ApplicationProvider>
    );
}


const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
});
