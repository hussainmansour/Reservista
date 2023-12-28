import {getBaseURL} from "../BaseURL";

import {handleRequest} from "../HandleRequest";
import {unAuthApi} from "../axiosIntsance";

const baseURL = getBaseURL() + '/auth';
export const AdminAPI = {
    saveVoucher: async (voucherDTO, onErrorCallback,setLoading) => {
        return await handleRequest(
            async () => {
                return await unAuthApi.post(
                    `${baseURL}/admin/voucher`,
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
                return await unAuthApi.post(
                    `${baseURL}/admin/newAdmin`,
                    adminDTO
                );
            },
            onErrorCallback,
            setLoading
        );
    }
}
