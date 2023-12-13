import axios from 'axios';
import  { useContext} from 'react';
import { AuthContext } from '../Store/authContext';
const BASE_URL = 'http://192.168.1.109:8080/user';

// const authCtx = useContext(AuthContext);


const userApiPostRequest = async (endpoint, data, setLoading) => {
    
    try {
      setLoading(true); // Set loading to true to show the ActivityIndicator
      console.log('Data', data);
      const response = await axios.post(`${BASE_URL}/${endpoint}`, data,{
        headers: {
            // 'Authorization': `Bearer ${authCtx.token}`,
            'Content-Type': 'application/json', 
        }
    });
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


const userApiGetRequest = async (endpoint, data, setLoading) => {
    
    try {
      setLoading(true); // Set loading to true to show the ActivityIndicator
      console.log('Data', data);
      const response = await axios.get(`${BASE_URL}/${endpoint}`, data,{
        headers: {
            // 'Authorization': `Bearer ${authCtx.token}`,
            'Content-Type': 'application/json', 
        }
    });
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

export const verifyVoucher = async (voucherCode, setLoading) => {
    return await userApiGetRequest('', voucherCode, setLoading);
};

export const reserve = async (reservationDTO, setLoading) => {
    return await userApiGetRequest('', reservationDTO, setLoading);
};

export const rollBackReservation = async (data, setLoading) => {
    return await userApiGetRequest('', data, setLoading);
};






  