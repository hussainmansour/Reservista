import axios from "axios";
const API_BASE_URL = 'http://192.168.1.17:8080/test/search/rooms';

const RoomAPI = {
    getRooms: async (HotelDTO) => {
        try {
            const response = await axios.post(API_BASE_URL, HotelDTO);
            return response.data;
        } catch (error) {
            throw error;
        }
    },
};

export default RoomAPI;