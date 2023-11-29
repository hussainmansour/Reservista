import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from './src/Screens/LoginScreen';
import SignupScreen from './src/Screens/SignUpForm';
import Home from './src/Screens/Home';



const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <View style={styles.container}>
      <StatusBar backgroundColor="white" barStyle="light-content" translucent={true} />
      <NavigationContainer>
        <Stack.Navigator>
          <Stack.Screen name = 'Login' options={{ headerShown: false }}   component = {LoginScreen}/>
          <Stack.Screen name = 'Signup' component = {SignupScreen}/>
          <Stack.Screen name = 'Home' component = {Home}/>
        </Stack.Navigator>
      </NavigationContainer>
      
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});
