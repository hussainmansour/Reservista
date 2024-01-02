import React, {useState, useEffect, useContext} from 'react';
import {View, Text, StyleSheet, TouchableOpacity, Modal} from 'react-native';
import {
    ActionsheetBackdrop,
    ActionsheetContent,
    ActionsheetDragIndicatorWrapper,
    ActionsheetDragIndicator,
    ActionsheetItem,
    ActionsheetItemText,
    ActionsheetIcon,
    Icon
} from "@gluestack-ui/themed"
import {
    ArrowDownNarrowWideIcon,
    ArrowUpWideNarrowIcon,
    PenSquare,
    Star
} from "lucide-react-native";
import {Actionsheet} from '@gluestack-ui/themed';
import Slider from "react-native-a11y-slider";
import {SearchCriteriaContext} from "../../Store/searchCriteriaContext";
import {SearchOptionsContext} from "../../Store/SearchOptionsContext";
import {SearchAndFilterAPI} from "../../Utilities/New/APIs/SearchAndFilterAPI";
import Color from "../../Styles/Color";


const SortAndFilterSelector = (
    {
        setHotels,
        setLoading
    }
) => {
    const [isModalVisible, setModalVisible] = useState(false);

    const [showActionSheet, setShowActionSheet] = useState(false)
    const [sortBy, setSortBy] = useState('')
    const [sortDirection, setSortDirection] = useState('')

    const [priceSliderRange, setPriceSliderRange] = useState([10, 10000]);
    const [ratingSliderRange, setRatingSliderRange] = useState([0, 10]);
    const [starsSliderRange, setStarsSliderRange] = useState([0, 5]);

    const [doneState, setDoneState] = useState(false);

    const {updateSearchCriteria, ...searchCriteria} = useContext(SearchCriteriaContext);
    const {updateSearchOptions, ...searchOptions} = useContext(SearchOptionsContext);


    const {
        selectedLocation,
        locations,
        checkInOutTimes,
        roomCount,
        travellersCount,
    } = searchOptions;


    const handlePriceChange = (values) => {
        setPriceSliderRange(values);
    }

    const handleRatingChange = (values) => {
        setRatingSliderRange(values);
    }

    const handleStarsChange = (values) => {
        setStarsSliderRange(values);
    }

    const handleClose = () => setShowActionSheet(!showActionSheet)

    const handlePriceLowToHigh = () => {
        setSortBy('price');
        setSortDirection('asc');
        handleClose();
    }
    const handlePriceHighToLow = () => {
        setSortBy('price');
        setSortDirection('desc');
        handleClose();
    }

    const handleTopRated = () => {
        setSortBy('rating');
        setSortDirection('desc');
        handleClose();
    }

    const handleStarRating = () => {
        setSortBy('stars');
        setSortDirection('desc');
        handleClose();
    }


    const onModalClose = () => {
        setModalVisible(false)
    }

    const handleFilterPress = () => {
        setModalVisible(true);
    };

    const getSearchCriteriaDTO = () => {
        return {
            city: locations[selectedLocation - 1].split("/")[0],
            country: locations[selectedLocation - 1].split("/")[1],
            numberOfRooms: roomCount,
            numberOfTravelers: travellersCount,
            pageNumber: 0,
            pageSize: 20,
            checkIn: checkInOutTimes["startDate"],
            checkOut: checkInOutTimes["endDate"],
            minPrice: priceSliderRange[0],
            maxPrice: priceSliderRange[1],
            minStars: starsSliderRange[0],
            maxStars: starsSliderRange[1],
            minRating: ratingSliderRange[0],
            maxRating: ratingSliderRange[1],
            sortBy: sortBy,
            sortOrder: sortDirection
        };
    }

    const [searchTriggered, setSearchTriggered] = useState(false);

    const search = async () => {
        updateSearchCriteria(getSearchCriteriaDTO());
        setSearchTriggered(true);
    };

    useEffect(() => {
        const fetchData = async () => {
            await search();
        };

        fetchData().then();
        setDoneState(false);
    }, [doneState]);

    useEffect(() => {

        if (searchTriggered) {
            const fetchData = async () => {
                let listOfHotels = await
                    SearchAndFilterAPI.filterAndSortHotels(searchCriteria, (response) => {

                        // in case of an expected error this should be the errorDTO
                        const responseBody = response.data;

                        if (responseBody.data !== undefined) {
                            // check for error code
                            if (responseBody.errorCode === 100) {
                                // todo : alert
                                console.log(responseBody)
                            }

                        } else {
                            // if it doesn't have the data attribute then it's not the errorDTO
                            // so, it's an unhandled exception
                            console.log(responseBody)
                        }

                    }, setLoading);
                setHotels(listOfHotels["hotels"]);
            };

            fetchData().then();
            setSearchTriggered(false);
            setDoneState(false);
        }
    }, [searchCriteria, searchTriggered, sortBy, sortDirection]);


    const handleDoneButton = () => {
        setDoneState(true);
        setModalVisible(false);
    }

    const handleCancelButton = () => {
        setModalVisible(false);
    }

    return (
        <View style={styles.container}>
            <TouchableOpacity style={styles.button} onPress={handleClose}>
                <Text style={styles.buttonText}>{"Sort"}</Text>
            </TouchableOpacity>

            <TouchableOpacity style={styles.button} onPress={handleFilterPress}>
                <Text style={styles.buttonText}>{"Filter"}</Text>
            </TouchableOpacity>

            <Actionsheet isOpen={showActionSheet} onClose={handleClose} zIndex={999}>
                <ActionsheetBackdrop/>
                <ActionsheetContent h="$72" zIndex={999} style={styles.actionSheet}>
                    <ActionsheetDragIndicatorWrapper>
                        <ActionsheetDragIndicator/>
                    </ActionsheetDragIndicatorWrapper>
                    <ActionsheetItem onPress={handlePriceLowToHigh}>
                        <ActionsheetIcon>
                            <Icon as={ArrowDownNarrowWideIcon}/>
                        </ActionsheetIcon>
                        <ActionsheetItemText style={styles.actionSheetTextElement}>Price: low to
                            high</ActionsheetItemText>
                    </ActionsheetItem>
                    <ActionsheetItem onPress={handlePriceHighToLow}>
                        <ActionsheetIcon>
                            <Icon as={ArrowUpWideNarrowIcon}/>
                        </ActionsheetIcon>
                        <ActionsheetItemText style={styles.actionSheetTextElement}>Price: high to
                            low</ActionsheetItemText>
                    </ActionsheetItem>
                    <ActionsheetItem onPress={handleTopRated}>
                        <ActionsheetIcon>
                            <Icon as={PenSquare}/>
                        </ActionsheetIcon>
                        <ActionsheetItemText style={styles.actionSheetTextElement}>Top rated</ActionsheetItemText>
                    </ActionsheetItem>
                    <ActionsheetItem onPress={handleStarRating}>
                        <ActionsheetIcon>
                            <Icon as={Star}/>
                        </ActionsheetIcon>
                        <ActionsheetItemText style={styles.actionSheetTextElement}>Star rating</ActionsheetItemText>
                    </ActionsheetItem>
                </ActionsheetContent>
            </Actionsheet>

            <Modal
                visible={isModalVisible}
                transparent
                animationType="slide"
                onRequestClose={onModalClose}
            >

                <View style={styles.modalContainer}>
                    <View style={styles.modalContent}>
                        <Text style={styles.modalTitles}>Price range</Text>
                        <Slider min={100} max={10000} values={[priceSliderRange[0], priceSliderRange[1]]}
                                onChange={handlePriceChange}/>

                        <Text style={styles.modalTitles}>Rating Range</Text>
                        <Slider min={1} max={10} values={[ratingSliderRange[0], ratingSliderRange[1]]}
                                onChange={handleRatingChange}/>

                        <Text style={styles.modalTitles}>Star Rating Range</Text>
                        <Slider min={1} max={5} values={[starsSliderRange[0], starsSliderRange[1]]}
                                onChange={handleStarsChange}/>

                        <View style={styles.modalButtons}>
                            <TouchableOpacity style={styles.modalButton} onPress={handleDoneButton}>
                                <Text style={styles.buttonText}>Done</Text>
                            </TouchableOpacity>

                            <TouchableOpacity style={styles.modalButton} onPress={handleCancelButton}>
                                <Text style={styles.buttonText}>Cancel</Text>
                            </TouchableOpacity>
                        </View>
                    </View>
                </View>
            </Modal>

        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        position: 'absolute',
        bottom: '5%',
        flexDirection: 'row',
        justifyContent: 'space-around',
        width: '90%',
    },
    button: {
        flex: 1,
        backgroundColor: Color.MIDNIGHTBLUE,
        borderRadius: 5,
        marginLeft: '10%',
        alignItems: 'center',
        alignSelf: 'center',
        justifyContent: 'center'
    },
    buttonText: {
        color: Color.DIRTYWHITE,
        fontSize: 18,
        marginLeft:15,
        marginRight:15,
        marginTop:3,
        marginBottom:3,
        borderRadius:2,

    },
    modalContainer: {
        flex: 1,
        marginTop: '20%',
        justifyContent: 'center',
        // height: '80%'
        // backgroundColor: 'rgba(0, 0, 0, 0.5)',
    },
    modalContent: {
        backgroundColor: Color.PALEBLUE,
        padding: 20,
        width: '80%',
        borderRadius: 20,
        justifyContent: 'center',
        alignItems: 'center',
        alignSelf: 'center'
    },
    actionSheet: {
        backgroundColor: Color.PALEBLUE,
        height: '26%'
    },
    actionSheetTextElement: {
        color: Color.MIDNIGHTBLUE,
        fontSize: 18,
    },
    modalButton: {
        backgroundColor: Color.SEABLUE,
        paddingHorizontal: '5%',
        borderRadius: 5,
        marginHorizontal: '3%'

    },
    modalTitles: {
        color: Color.MIDNIGHTBLUE,
        fontSize: 20,
    },
    modalButtons: {
        marginTop: '10%',
        flexDirection: 'row',
        justifyContent: 'space-between'
    },
    doneSheetText: {
        alignSelf: 'center'
    }
});

export default SortAndFilterSelector;
