import React from 'react';
import { View, ActivityIndicator, StyleSheet } from 'react-native';
import Color from '../../Styles/Color';

const LoadingComponent = () => {
  return (
    <View style={styles.loadingContainer}>
      <ActivityIndicator size="large" color={Color.MIDNIGHTBLUE} />
    </View>
  );
};

const styles = StyleSheet.create({
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor:Color.PALEBLUE
  },
});

export default LoadingComponent;
