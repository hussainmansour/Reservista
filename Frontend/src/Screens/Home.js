import {Dimensions, StyleSheet, Text, View} from 'react-native'
import React from 'react'
import HomeHeader from "../Components/HomeHeader";
import SearchOptions from "../Components/SearchOptions";

const Home = ({navigation}) => {
  return (
    <View style = {styles.container}>
        <HomeHeader navigation={navigation}/>
        <SearchOptions navigation={navigation}/>
        {/*<View style={styles.tabs}>*/}
        {/*    <Text style={{fontSize: 25}}>Tab Navigator</Text>*/}
        {/*</View>*/}
    </View>
  )
}


export default Home

const screenHeight = Dimensions.get('window').height;

const styles = StyleSheet.create({
    container:{
        flex: 1,
        justifyContent: 'flex-start',
        backgroundColor: 'white'
    },
    searchOptions: {
        position: 'absolute',
        bottom: '30%',
        left: '10%'
    },
    tabs: {
        marginTop: '100%',
        alignSelf: 'center',
        borderColor: 'blue',
        borderWidth: 3,
        paddingHorizontal: '25%',
        paddingVertical: '1%',
        borderRadius: 18
    }
})
