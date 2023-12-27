import React, {createContext, useEffect, useState} from 'react';

export const SearchCriteriaContext = createContext({
    city: '',
    country: '',
    numberOfRooms: 0,
    numberOfTravelers: 0,
    pageNumber: 0,
    pageSize: 20,
    checkIn: '',
    checkOut: '',
    minPrice: 0,
    maxPrice: 10000,
    minStars: 0,
    maxStars: 5,
    minRating: 0.0,
    maxRating: 10.0,
    sortBy: "",
    sortOrder: "",
    updateSearchCriteria: (newCriteria) => {},
});

const SearchCriteriaContextProvider = ({children}) => {
    const [searchCriteria, setSearchCriteria] = useState({
        city: '',
        country: '',
        numberOfRooms: 0,
        numberOfTravelers: 0,
        pageNumber: 0,
        pageSize: 20,
        checkIn: '',
        checkOut: '',
        minPrice: 0,
        maxPrice: 10000,
        minStars: 0,
        maxStars: 5,
        minRating: 0.0,
        maxRating: 10.0,
        sortBy: "",
        sortOrder: "",
        updateSearchCriteria: (newCriteria) => {}
    });

    const updateSearchCriteria = (newCriteria) => {
        setSearchCriteria((prevCriteria) => ({
            ...prevCriteria,
            ...newCriteria
        }));
    };

    const value = {
        ...searchCriteria,
        updateSearchCriteria,
    };

    return (
        <SearchCriteriaContext.Provider value={value}>
            {children}
        </SearchCriteriaContext.Provider>
    );
};

export default SearchCriteriaContextProvider;
