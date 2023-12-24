import React from 'react';
import { TouchableOpacity ,StyleSheet } from 'react-native';
import ScalableText from 'react-native-text';

const CounterButton = ({
                    count,
                    type,
                    onPress,
                    disabled,
                    minusIcon,
                    plusIcon,
                    buttonStyle,
                    buttonTextStyle,
                }) => {
    const isDisabled = () => {
        if (disabled) {
            return true;
        }

        return (type === '-' ? 0 : 10) === count;
    };

    const isMinus = () => type === '-';
    const isPlus = () => type === '+';

    const icon = () => {
        const icon = isMinus() ? minusIcon : plusIcon;

        if (icon) {
            return icon(isDisabled());
        }

        return (
            <ScalableText styles={[styles.buttonText, buttonTextStyle]}>
                {isMinus() ? '-' : '+'}
            </ScalableText>
        );
    };

    const style = {
        opacity: isDisabled() ? 0.2 : 1,
        ...buttonStyle,
    };

    return (
        <TouchableOpacity
            style={[styles.touchable, style]}
            onPress={() => onPress(isMinus() ? count - 1 : count + 1, type)}
            disabled={isDisabled()}
        >
            {icon()}
        </TouchableOpacity>
    );
};

const styles = StyleSheet.create({
    touchable: {
        minWidth: 35,
        minHeight: 35,
        borderWidth: 1,
        borderColor: '#27AAE1',
        borderRadius: 5,
        alignItems: 'center',
        justifyContent: 'center',
    },

    buttonText: {
        fontSize: 15,
        color: '#27AAE1',
    }
});

export default CounterButton;
