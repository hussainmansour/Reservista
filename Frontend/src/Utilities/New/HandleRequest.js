export const handleRequest = async (apiCall, onErrorCallback, setLoading) => {
    try {
        if (setLoading && typeof setLoading === 'function'){
            setLoading(true);
        }

        const response = await apiCall();
        console.log("in the call");
        console.log(response.data);
        return response.data;
    } catch (error) {
        console.log('API Request Error:', error);

        if (onErrorCallback && typeof onErrorCallback === 'function')
            onErrorCallback(error.response);
    }
    finally{
        if (setLoading && typeof setLoading === 'function'){
            console.log('====================================');
            console.log("setting is loading to false");
            console.log('====================================');
            setLoading(false);
        }
    }
};
