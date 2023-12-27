import {NetworkInfo} from "react-native-network-info";

export const getBaseURL = () => {
    let ipv4Address = "192.168.1.109";
    // try {
    //     ipv4Address = await NetworkInfo.getIPV4Address();
    // } catch (error) {
    //     console.error('Error getting IP address:', error);
    // }
    return `http://${ipv4Address}:8080`;
};
