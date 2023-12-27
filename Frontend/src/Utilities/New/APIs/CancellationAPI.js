import {getBaseURL} from "../BaseURL";
import {authApi} from "../axiosIntsance";
import {handleRequest} from "../HandleRequest";

const baseURL = getBaseURL() + '/user';

export const CancellationAPI = {
    cancelReservation: async (reservationId, onErrorCallback , setLoading) => {
        return await handleRequest(
            async () => {
                return await authApi.post(
                    `${baseURL}/cancel-reservation`,
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
    },
    getRefundedAmount: async (reservationId, onErrorCallback, setLoading) => {
        return await handleRequest(
            async () => {
                return await authApi.post(
                    `${baseURL}/get-refunded-amount`,
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
