import React, { createContext, useState } from 'react';

export const SearchOptionsContext = createContext({
    selectedLocation: 0,
    locations: [],
    checkInOutTimes: {},
    roomCount: 0,
    travellersCount: 0,
    updateSearchOptions: (newOptions) => {},
});

export const SearchOptionsContextProvider = ({ children }) => {
    const [searchOptions, setSearchOptions] = useState({
        selectedLocation: 0,
        locations: [],
        checkInOutTimes: {},
        roomCount: 0,
        travellersCount: 0,
    });

    const updateSearchOptions = (newOptions) => {
        setSearchOptions((prevOptions) => ({
            ...prevOptions,
            ...newOptions,
        }));
    };

    const value = {
        ...searchOptions,
        updateSearchOptions,
    };

    return (
        <SearchOptionsContext.Provider value={value}>
            {children}
        </SearchOptionsContext.Provider>
    );
};

export default SearchOptionsContextProvider;
