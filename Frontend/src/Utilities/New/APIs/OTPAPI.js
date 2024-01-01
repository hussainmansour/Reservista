import {getBaseURL} from "../BaseURL";
import {handleRequest} from "../HandleRequest";
import {unAuthApi} from "../axiosIntsance";

const baseURL = getBaseURL() + '/auth';
export const OTPAPI = {
    verifyGmailAccount: async (codeVerificationDTO, onErrorCallback, setLoading) => {
        return await handleRequest(
            async () => {
                return await unAuthApi.post(
                    `${baseURL}/verify-code`,
                    codeVerificationDTO
                );
            },
            onErrorCallback,
            setLoading
        )
    },
    refreshOTP: async (email, onErrorCallback, setLoading) => {
        console.log("email in refreshOTP",email)
        return await handleRequest(
            async () => {
                return await unAuthApi.post(
                    `${baseURL}/refresh-verification-code/${email}`
                );
            },
            onErrorCallback,
            setLoading
        )
    }
}
