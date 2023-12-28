import {getBaseURL} from "../BaseURL";

import {handleRequest} from "../HandleRequest";
import {authApi, unAuthApi} from "../axiosIntsance";

const baseURL = getBaseURL() + '/admin';
export const AdminAPI = {
    saveVoucher: async (voucherDTO, onErrorCallback,setLoading) => {
        return await handleRequest(
            async () => {
                return await authApi.post(
                    `${baseURL}/voucher`,
                    voucherDTO
                );
            },
            onErrorCallback,
            setLoading
        );
    },
    saveAdmin:async (adminDTO, onErrorCallback,setLoading) => {
        return await handleRequest(
            async () => {
                return await authApi.post(
                    `${baseURL}/newAdmin`,
                    adminDTO
                );
            },
            onErrorCallback,
            setLoading
        );
    }
}
