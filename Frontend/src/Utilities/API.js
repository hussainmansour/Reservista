// api.js
import { ActivityIndicator } from 'react-native';
import axios from 'axios';

const BASE_URL = 'https://api.example.com';

export const signUp = async (userData, setLoading) => {
    try {
        setLoading(true); // Set loading to true to show the ActivityIndicator

        const response = await axios.post(`${BASE_URL}/signup`, userData);

        if (response.data.status === 'ok') {
            return response.data;
        } else {
            throw new Error(response.data.message);
        }
    } catch (error) {
        throw new Error('Error signing up. Please try again.');
    } finally {
        setLoading(false); // Set loading to false to hide the ActivityIndicator, regardless of success or failure
    }
};

export const verifyEmail = async (verificationCode, setLoading) => {
    try {
        setLoading(true); // Set loading to true to show the ActivityIndicator

        const response = await axios.post(`${BASE_URL}/verify-email`, { verificationCode });

        if (response.data.status === 'ok') {
            return response.data;
        } else {
            throw new Error(response.data.message);
        }
    } catch (error) {
        throw new Error('Error verifying email. Please try again.');
    } finally {
        setLoading(false); // Set loading to false to hide the ActivityIndicator, regardless of success or failure
    }
};

export const signIn = async (userInfo, setLoading) => {
    try {
        setLoading(true); // Set loading to true to show the ActivityIndicator

        const response = await axios.post(`${BASE_URL}/signin`, userInfo);

        if (response.data.status === 'ok') {
            return response.data;
        } else {
            throw new Error(response.data.message);
        }
    } catch (error) {
        throw new Error('Error signing up. Please try again.');
    } finally {
        setLoading(false); // Set loading to false to hide the ActivityIndicator, regardless of success or failure
    }
};
