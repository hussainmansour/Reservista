import {getBaseURL} from "../BaseURL";
import {handleRequest} from "../HandleRequest";
import {unAuthApi} from "../axiosIntsance";

const baseURL = getBaseURL() + '/config';
export const ConfigAPI = {
    getValidCountries: async (onErrorCallback) => {
        return await handleRequest(
            async () => {
                return await unAuthApi.get(
                    `${baseURL}/countries`,
                );
            },
            onErrorCallback
        );
    },
    getValidLocations: async (onErrorCallback) => {
        return await handleRequest(
            async () => {
                return await unAuthApi.get(
                    `${baseURL}/locations`,
                );
            },
            onErrorCallback
        );
    }
}
