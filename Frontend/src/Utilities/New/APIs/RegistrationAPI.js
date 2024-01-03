import {getBaseURL} from "../BaseURL";
import {handleRequest} from "../HandleRequest";
import {unAuthApi} from "../axiosIntsance";

const baseURL = getBaseURL() + '/auth';
export const RegistrationAPI = {
    register : async (registrationRequestDTO,onErrorCallback, setLoading) => {
        return await handleRequest(
            async () => {
                return await unAuthApi.post(
                    `${baseURL}/register`,
                    registrationRequestDTO
                );
            },
            onErrorCallback,
            setLoading
        )
    }
}
