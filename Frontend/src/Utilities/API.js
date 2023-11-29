// api.js
import {ActivityIndicator} from "react-native";
import axios from 'axios';

const BASE_URL = 'https://api.example.com';


export const signUp = async (userData) => {
    try {
        const response = await axios.post(`${BASE_URL}/signup`, userData);

        if (response.data.status === 'ok') {
            return response.data;
        } else {
            throw new Error(response.data.message);
        }
    } catch (error) {
        throw new Error('Error signing up. Please try again.');
    }
};

export const verifyEmail = async (verificationCode) => {
    try {
        const response = await axios.post(`${BASE_URL}/verify-email`, { verificationCode });

        if (response.data.status === 'ok') {
            return response.data;
        } else {
            throw new Error(response.data.message);
        }
    } catch (error) {
        throw new Error('Error verifying email. Please try again.');
    }
};
