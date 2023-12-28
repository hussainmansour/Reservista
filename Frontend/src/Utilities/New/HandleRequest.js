export const handleRequest = async (apiCall, onErrorCallback, setLoading) => {
    try {
        if (setLoading && typeof setLoading === 'function')
            setLoading(true);

        const response = await apiCall();

        if (setLoading && typeof setLoading === 'function')
            setLoading(false);

        return response.data;
    } catch (error) {
        console.log('API Request Error:', error);

        if (setLoading && typeof setLoading === 'function')
            setLoading(false);

        if (onErrorCallback && typeof onErrorCallback === 'function')
            onErrorCallback(error.response);
    }
};
