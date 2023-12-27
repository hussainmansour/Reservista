import {getBaseURL} from "../BaseURL";
import {authApi} from "../axiosIntsance";
import {handleRequest} from "../HandleRequest";

const baseURL = getBaseURL() + '/user/profile';

export const ProfileAPI = {
    viewProfile: async (onErrorCallback, setLoading) => {
        return await handleRequest(
            async () => {
                return await authApi.get(
                    `${baseURL}/view`
                );
            },
            onErrorCallback,
            setLoading
        )
    },
    editProfile: async (profileUpdateDTO, onErrorCallback, setLoading) => {
        return await handleRequest(
            async () => {
                return await authApi.put(
                    `${baseURL}/edit`,
                    profileUpdateDTO
                );
            },
            onErrorCallback,
            setLoading
        )
    }
}
