import axios from 'axios';

// Remember to add your IP address
const BASE_URL = 'http://192.168.1.109:8080';

const apiRequest = async (endpoint, data, setLoading) => {
  try {
    setLoading(true); // Set loading to true to show the ActivityIndicator
    console.log('Data', data);
    const response = await axios.post(`${BASE_URL}/${endpoint}`, data);
    console.log('response at line 10', response.status)
    if (response.status === 200) {
      console.log("data" + response.data)
      return response.data;
    } else {
      throw new Error(response.data.message);
    }
  } catch (error) {
    throw new Error(`Error ${endpoint.replace('-', ' ')}: ${error.message}`);
  } finally {
    setLoading(false); // Set loading to false to hide the ActivityIndicator, regardless of success or failure
  }
};

export const signUp = async (userData, setLoading) => {
  return await apiRequest('auth/register', userData, setLoading);

};

export const verifyEmail = async (dto, setLoading) => {
  return await apiRequest('auth/verify-code', dto, setLoading);
};

export const signIn = async (userInfo, setLoading) => {
  console.log(userInfo);
  return await apiRequest('auth/login', userInfo, setLoading);
};

export const refreshCode = async (userInfo, setLoading) => {
  return await apiRequest('auth/refresh-verification-code', userInfo, setLoading);
};

export const searchForHotels = async (searchDTO, setLoading) => {
  return await apiRequest('test/search/hotels', searchDTO, setLoading);
}
