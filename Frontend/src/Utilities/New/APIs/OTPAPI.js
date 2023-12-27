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
        return await handleRequest(
            async () => {
                return await unAuthApi.post(
                    `${baseURL}/refresh-verification-code`,
                    null,
                    {
                        params: {
                            'email': email
                        }
                    }
                );
            },
            onErrorCallback,
            setLoading
        )
    }
}
