import axios from "axios";
const API_BASE_URL = 'http://192.168.1.17:8080/user/profile';
const VIEW_PATH = '/view';
const EDIT_PATH = '/edit';

const ProfileAPI = {
    getProfile: async (authorizationToken) => {
        try {
            const response = await axios.get(API_BASE_URL + VIEW_PATH, {
                headers: {
                    'Authorization': `Bearer ${authorizationToken}`,
                    'Content-Type': 'application/json', // Adjust content type if needed
                }
            });
            return response.data;
        } catch (error) {
            throw error;
        }
    },

    updateProfile: async (authorizationToken, profileData) => {
        try {
            const response = await axios.put(API_BASE_URL + EDIT_PATH, profileData, {
                headers: {
                    'Authorization': `Bearer ${authorizationToken}`,
                    'Content-Type': 'application/json', // Adjust content type if needed
                }
            });
            return response.data;
        } catch (error) {
            throw error;
        }
    },
};

export default ProfileAPI;
