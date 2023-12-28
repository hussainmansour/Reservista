import {authApi} from "../axiosIntsance";
import {getBaseURL} from "../BaseURL";
import {handleRequest} from "../HandleRequest";

const baseURL = getBaseURL() + '/user';

export const ReservationAPI = {
    applyVoucher: async (voucherCode, onErrorCallback, setLoading) => {
        return await handleRequest(
            async () => {
                return await authApi.post(
                    `${baseURL}/apply-voucher`,
                    null,
                    {
                        params: {
                            'voucherCode': voucherCode
                        }
                    }
                );
            },
            onErrorCallback,
            setLoading
        )
    },
    reserve: async (reservationDTO, onErrorCallback, setLoading) => {
        return await handleRequest(
            async () => {
                return await authApi.post(
                    `${baseURL}/reserve`,
                    reservationDTO
                );
            },
            onErrorCallback,
            setLoading
        )
    },
    rollback: async (reservationId,onErrorCallback, setLoading) => {
        return await handleRequest(
            async () => {
                return await authApi.post(
                    `${baseURL}/rollback`,
                    null,
                    {
                        params: {
                            'reservationId': reservationId
                        }
                    }
                );
            },
            onErrorCallback,
            setLoading
        )
    }
}
