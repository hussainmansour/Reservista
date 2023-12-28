import {
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  
} from "react-native";
import Collapsible from "react-native-collapsible";
import Icon from "react-native-vector-icons/Ionicons";
import Checkbox from "expo-checkbox";
import Color from "../../Styles/Color.js";
import styles from '../../Styles/ReservationStyles.js';

const AdditionalOptionsCollapse = ({ room, expanded, onToggle, onBreakfastToggle, onLunchToggle, onDinnerToggle, foodOptions }) => {
  return (
    <View style={styles.container} >
      <TouchableOpacity onPress={onToggle}>
        <View style={styles.foodInfoContainer}>
          <Icon
            name={expanded ? 'chevron-up-outline' : 'chevron-down-outline'}
            size={24}
            color={ Color.MIDNIGHTBLUE}//"#0766AD"
          />
          <Text style={styles.roomTitle}>{room.title}</Text>
          <Text style={styles.roomTitle}>${room.price}</Text>
        </View>
      </TouchableOpacity>

      <Collapsible collapsed={!expanded}>
        <View>
          <View style={styles.horizontalLine} />
          <View style={styles.foodInfoContainer}>
            <View style={styles.textContainer}>
              <Text style={styles.optionsText2}>Breakfast</Text>
            </View>
            <View style={styles.checkboxContainerAdditionalOption}>
              <Text style={styles.optionsText2}>(+${foodOptions.breakfastPrice})</Text>
              <Checkbox 
                style={styles.checkboxStyle}
                value={room.hasBreakfast}
                color= { Color.MIDNIGHTBLUE}//"#3081D0"
                onValueChange={() => onBreakfastToggle(room.id-1)} />  
            </View>
          </View>
          <View style={styles.foodInfoContainer}>
            <View style={styles.textContainer}>
              <Text style={styles.optionsText2}>Lunch</Text>
            </View>
            <View style={styles.checkboxContainerAdditionalOption}>
              <Text style={styles.optionsText2}>(+${foodOptions.lunchPrice})</Text>
              <Checkbox 
                style={styles.checkboxStyle}
                value={room.hasLunch}
                color= { Color.MIDNIGHTBLUE}//"#3081D0"
                onValueChange={() => onLunchToggle(room.id-1)} />  
            </View>
          </View>
          <View style={styles.foodInfoContainer}>
            <View style={styles.textContainer}>
              <Text style={styles.optionsText2}>Dinner</Text>
            </View>
            <View style={styles.checkboxContainerAdditionalOption}>
              <Text style={styles.optionsText2}>(+${foodOptions.dinnerPrice})</Text>
              <Checkbox 
                style={styles.checkboxStyle}
                value={room.hasDinner}
                color= { Color.MIDNIGHTBLUE}//"#3081D0"
                onValueChange={() => onDinnerToggle(room.id-1)} />  
            </View>
          </View>
        </View>
      </Collapsible>
    </View>
  );
};

  
export default AdditionalOptionsCollapse;
