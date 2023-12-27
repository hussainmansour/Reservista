import axios from "axios";

const API_BASE_URL = 'http://192.168.1.109:8080/test/search/rooms';


const RoomAPI = {
    getRooms: async (HotelDTO) => {
        try {
            console.log("from the con")
            console.log(HotelDTO);
            const response = await axios.post(API_BASE_URL, HotelDTO);
            return response.data;
        } catch (error) {
            throw error;
        }
    },
};

export default RoomAPI;
