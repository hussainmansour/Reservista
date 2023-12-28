import axios from "axios";
import {getBaseURL} from "./BaseURL";

export const authApi = axios.create({
    baseURL: getBaseURL(),
    // withCredentials: true,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const unAuthApi = axios.create({
    baseURL: getBaseURL(),
    headers: {
        'Content-Type': 'application/json',
    },
});

