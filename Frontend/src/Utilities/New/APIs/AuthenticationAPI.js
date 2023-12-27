import {getBaseURL} from "../BaseURL";

import {handleRequest} from "../HandleRequest";
import {unAuthApi} from "../axiosIntsance";

const baseURL = getBaseURL() + '/auth';
export const AuthenticationAPI = {
    login: async (authenticationRequestDTO, onErrorCallback,setLoading) => {
        return await handleRequest(
            async () => {
                return await unAuthApi.post(
                    `${baseURL}/login`,
                    authenticationRequestDTO
                );
            },
            onErrorCallback,
            setLoading
        );
    }
}
