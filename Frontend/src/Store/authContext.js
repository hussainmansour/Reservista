import AsyncStorage from '@react-native-async-storage/async-storage';
import {decode as atob} from "base-64";
import { createContext, useState ,useEffect} from 'react';
import {authApi} from "../Utilities/New/axiosIntsance";

export const AuthContext = createContext({
    token: '',
    isAdmin:false,
    isAuthenticated: false,
    authenticate: () => {},
    logout: () => {},
});

function AuthContextProvider({ children }) {
    const [authToken, setAuthToken] = useState();
    const [isAdmin, setIsAdmin] = useState();

    function authenticate(token) {
        setAuthToken(token);
        setIsAdmin(JSON.parse(atob(token.split(".")[1])).role ==='ADMIN');
        AsyncStorage.setItem('token', token);
    }

    function logout() {
        setAuthToken(null);
        AsyncStorage.removeItem('token');
    }

    const value = {
        token: authToken,
        isAdmin:isAdmin,
        isAuthenticated: !!authToken,
        authenticate: authenticate,
        logout: logout,
    };
    useEffect(() => {
        const requestInterceptor = authApi.interceptors.request.use(
            (config) => {
                if (authToken) {
                    config.headers['Authorization'] = `Bearer ${authToken}`;
                }
                return config;
            },
            (error) => {
                return Promise.reject(error);
            }
        );

        return () => {
            authApi.interceptors.request.eject(requestInterceptor);
        };
    }, [authToken]);

    useEffect(() => {
        const requestInterceptor = authApi.interceptors.request.use(
            (config) => {
                if (authToken) {
                    config.headers['Authorization'] = `Bearer ${authToken}`;
                }
                return config;
            },
            (error) => {
                return Promise.reject(error);
            }
        );

        return () => {
            authApi.interceptors.request.eject(requestInterceptor);
        };
    }, [authToken]);

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export default AuthContextProvider;
