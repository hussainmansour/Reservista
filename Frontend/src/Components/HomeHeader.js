import React, {useEffect, useState} from 'react';
import {StyleSheet, Text, TouchableOpacity, View} from "react-native";
import {
    Avatar, AvatarFallbackText, AvatarImage,
    Icon
} from "@gluestack-ui/themed"
import {User} from "lucide-react-native";

const HomeHeader = () => {

    const [profileImage, setProfileImage] = useState(null);
    const [firstName, setFirstName] = useState('Mohamed');
    const [lastName, setLastName] = useState('Anwar');
    const [hasImage, setHasImage] = useState(false);


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
        // navigate to profile
        console.log("profile")
    }


    return (
        <View style={styles.header}>
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
        backgroundColor: '#4536F9',
        borderBottomLeftRadius: 40,
        borderBottomRightRadius: 40,
    }
    ,
    headerTitle: {
        fontWeight: '900',
        fontSize: 45,
        color: '#0c0202'
    },
    avatar: {
        alignSelf: 'flex-end',
        paddingBottom: '65%',
        paddingRight: '10%'
    }
})
export default HomeHeader;
