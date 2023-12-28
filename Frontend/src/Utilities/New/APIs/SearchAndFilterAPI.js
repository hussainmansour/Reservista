import {getBaseURL} from "../BaseURL";
import {authApi} from "../axiosIntsance";
import {handleRequest} from "../HandleRequest";

const baseURL = getBaseURL() + '/user/search';

export const SearchAndFilterAPI = {
    filterAndSortHotels: async (hotelSearchCriteriaDTO, onErrorCallback, setLoading) => {
        return await handleRequest(
            async () => {
                return await authApi.post(
                    `${baseURL}/hotels`,
                    hotelSearchCriteriaDTO
                );
            },
            onErrorCallback,
            setLoading
        )
    },
    getHotel: async (hotelIdentifierWithSearchCriteriaDTO, onErrorCallback, setLoading) => {
        console.log("In SearchAPI");
        return await handleRequest(
            async () => {
                return await authApi.post(
                    `${baseURL}/hotel`,
                    hotelIdentifierWithSearchCriteriaDTO
                );
            },
            onErrorCallback,
            setLoading
        )
    }
}
