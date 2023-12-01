import axios from 'axios';

const BASE_URL = 'https://api.example.com';

const apiRequest = async (endpoint, data, setLoading) => {
  try {
    setLoading(true); // Set loading to true to show the ActivityIndicator

    const response = await axios.post(`${BASE_URL}/${endpoint}`, data);

    if (response.data.status === 'ok') {
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
  return apiRequest('signup', userData, setLoading);
};

export const verifyEmail = async (verificationCode, setLoading) => {
  return apiRequest('verify-email', { verificationCode }, setLoading);
};

export const signIn = async (userInfo, setLoading) => {
  return apiRequest('signin', userInfo, setLoading);
};