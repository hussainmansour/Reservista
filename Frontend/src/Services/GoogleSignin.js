// import { StyleSheet, Text, View } from 'react-native'
// import * as WebBrowser from "expo-web-browser"
// import * as Google from 'expo-auth-session/providers/google'
// import * as React from 'react'
//
// const GoogleSignIn = () => {
//     const [userInfo, setUserInfo] = React.useState(null)
//     const [request, response, promptAsync] = Google.useAuthRequest({
//       androidClientId: "1031607216981-56eug3c6kc0m0opdoo880f46aimrakga.apps.googleusercontent.com",
//       webClientId: "1031607216981-a811ens5029nos4vo5qgkjm99imtmlcg.apps.googleusercontent.com"
//     })
//
//     React.useEffect( () => {handleGoogleSignup();}, [response]);
//
//
//     async function handleGoogleSignup(){
//       if(response?. type === "success"){
//         console.log(response.authentication.idToken)
//         await getUserInfo(response.authentication.accessToken)
//       }else{
//         console.log("Failed")
//       }
//     }
//
//     const getUserInfo = async (token) => {
//       if(!token) return;
//       try{
//         const response = await fetch(
//           "https://www.googleapis.com/userinfo/v2/me",
//           {
//             headers : { Authorization: `Bearer ${token}` }
//           }
//         );
//
//         const user = await response.json();
//         setUserInfo(user)
//       }catch(error){
//         console.log('Failed')
//       }
//     }
//
//   return (
//     <View>
//       <TouchableOpacity style = {styles.googleButtonContainer} onPress={() => promptAsync()}>
//         <Text style = {styles.googleText} >Continue with Google</Text>
//         <Image source = {require('../Data/Google.png')} style = {styles.img}/>
//       </TouchableOpacity>
//     </View>
//   )
// }
//
// export default GoogleSignIn
//
// const styles = StyleSheet.create({
//     googleButtonContainer:{
//         backgroundColor: '#728FF3',
//         alignSelf: 'center',
//         marginTop: 30,
//         width: 240,
//         height: 43,
//         borderRadius: 10,
//         justifyContent: 'space-evenly',
//         flexDirection: 'row',
//         paddingTop: 10
//     },
//     googleText:{
//         color: 'white',
//         fontSize: 15,
//         paddingLeft: 25
//     },
//     img:{
//         width:28,
//         height: 25,
//     }
// })
