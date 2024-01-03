import React, { useState } from 'react';
import { View, StyleSheet } from 'react-native';
import CounterButton from "./CounterButton";
import ScalableText from "react-native-text";
import Color from "../../Styles/Color";

const Counter = ({
    count,
    setCount,
    minus = '-',
    plus = '+',
    min = 0,
    max = 10,
    onChange,
    onChangeBefore = null,
    minusIcon = null,
    plusIcon = null,
    buttonStyle = {},
    buttonTextStyle = {},
    countTextStyle = {},
}) => {

    const [beforeLoading, setBeforeLoading] = useState(false);

    const onPress = (count, type) => {
        if (onChangeBefore) {
            setBeforeLoading(true);
            onChangeBefore(() => {
                setBeforeLoading(false);
                handleChange(count, type);
            });
        } else {
            handleChange(count, type);
        }
    };

    const handleChange = (count, type) => {
        setCount(count);
        onChange && onChange(count, type);
    };

    return (
        <View style={counterStyles.container}>
            <CounterButton
                type="-"
                count={count}
                onPress={onPress}
                disabled={beforeLoading}
                minusIcon={minusIcon}
                buttonStyle={buttonStyle}
                buttonTextStyle={buttonTextStyle}
            />

            <View style={counterStyles.count}>
                <ScalableText styles={[counterStyles.countText, countTextStyle]}>
                    {count}
                </ScalableText>
            </View>

            <CounterButton
                type="+"
                count={count}
                onPress={onPress}
                disabled={beforeLoading}
                plusIcon={plusIcon}
                buttonStyle={buttonStyle}
                buttonTextStyle={buttonTextStyle}
            />
        </View>
    );
};

const counterStyles = StyleSheet.create({
    container: {
        flexDirection: 'row',
        color: Color.SEABLUE,

    },
    countText: {
        fontSize: 16,
        paddingLeft: 15,
        paddingRight: 15,
        color: Color.SEABLUE,
    },
    count: {
        minWidth: 40,
        alignItems: 'center',
        justifyContent: 'center',
        color: Color.SEABLUE,

    },
});

export default Counter;
