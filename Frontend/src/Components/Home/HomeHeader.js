import React, {useContext, useEffect, useState} from 'react';
import {Button, StyleSheet, Text, TouchableOpacity, View} from "react-native";
import {
    Avatar, AvatarFallbackText, AvatarImage,
    Icon
} from "@gluestack-ui/themed"
import {User} from "lucide-react-native";
import CustomizedButton from "../General/Buttons/CustomizedButton";
import IconButton from "../General/Buttons/IconButton";
import {AuthContext} from "../../Store/authContext";
import Color from "../../Styles/Color";

const HomeHeader = ({navigation}) => {

    const [profileImage, setProfileImage] = useState(null);
    const [firstName, setFirstName] = useState('Mohamed');
    const [lastName, setLastName] = useState('Anwar');
    const [hasImage, setHasImage] = useState(false);

    const authCtx = useContext(AuthContext);


    const getProfileImage = async () => {
        setProfileImage(
            "https://images.unsplash.com/photo-1633332755192-727a05c4013d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8dXNlcnxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=800&q=60")
    }

    const hasProfileImage = async () => {
        setHasImage(true);
    }

    const getFirstAndLastName = async () => {

    }


    useEffect(() => {
        const fetchData = async () => {
            await getProfileImage();
            await hasProfileImage();
            await getFirstAndLastName();
        };

        fetchData();
    }, []);

    const onPress = () => {
        navigation.navigate('Profile');
        console.log("profile")
    }


    return (
        <View style={styles.header}>

            <View style={styles.logoutButton} >
                <IconButton
                                icon="exit"
                                color={Color.SEABLUE}
                                size={24}
                                onPress={authCtx.logout}
                            />
                {/*<Text style={styles.buttonText}>{"Logout"}</Text>*/}
            </View>


            <Text style={styles.headerTitle}>
                {`Let's Start \nExploring`}
            </Text>

            <View style={styles.avatar}>
                <TouchableOpacity onPress={onPress}>
                    <Avatar size="md">
                        {hasImage ? (
                            <AvatarImage source={{uri: profileImage}} alt={profileImage}/>
                        ) : (
                            <AvatarFallbackText>{`${firstName[0]} ${lastName[0]}`}</AvatarFallbackText>
                        )}
                    </Avatar>
                </TouchableOpacity>
            </View>
        </View>
    );
};

const styles = StyleSheet.create({
    header: {
        flexDirection: 'row',
        paddingLeft: '10%',
        paddingTop: '25%',
        justifyContent: 'space-between',
        alignItems: 'flex-start',
        width: '100%',
        height: '40%',
        backgroundColor: Color.SEABLUE,
        borderBottomLeftRadius: 40,
        borderBottomRightRadius: 40,
    }
    ,
    headerTitle: {
        fontWeight: '900',
        fontSize: 45,
        color: Color.DIRTYWHITE,
        marginTop: '5%'
    },
    avatar: {
        alignSelf: 'flex-end',
        paddingBottom: '65%',
        paddingRight: '10%'
    },
    logoutButton : {
        backgroundColor: Color.SEABLUE,
        borderRadius: 10,
        height: '18%',
        width: '12%',
        justifyContent: 'center',
        bottom: '9%',
    },
    buttonText: {
        color: '#000000',
        fontSize: 10,
    }
})
export default HomeHeader;
