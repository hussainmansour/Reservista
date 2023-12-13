import {
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  
} from "react-native";
import Collapsible from "react-native-collapsible";
import Icon from "react-native-vector-icons/Ionicons";
import Checkbox from "expo-checkbox";

const AdditionalOptionsCollapse = ({ room, expanded, onToggle, onBreakfastToggle, onLunchToggle, onDinnerToggle, foodOptions }) => {
  return (
    <View style={styles.container} >
      <TouchableOpacity onPress={onToggle}>
        <View style={styles.foodInfoContainer}>
          <Icon
            name={expanded ? 'chevron-up-outline' : 'chevron-down-outline'}
            size={24}
            color="#131155"
          />
          <Text style={styles.roomTitle}>{room.title}</Text>
          <Text style={styles.roomTitle}>${room.price}</Text>
        </View>
      </TouchableOpacity>

      <Collapsible collapsed={!expanded}>
        <View>
          <View style={styles.horizontalLine} />
          <Text style={styles.additionalOptionsLabel}>
            Additional Food Options:
          </Text>
          <View style={styles.foodInfoContainer}>
            <View style={styles.textContainer}>
              <Text style={styles.optionsText}>Breakfast</Text>
            </View>
            <View style={styles.checkboxContainer}>
              <Text style={styles.optionsText}>(+${foodOptions.breakfastPrice})</Text>
              <Checkbox 
                style={styles.checkboxStyle}
                value={room.hasBreakfast}
                color= "#131155"
                onValueChange={() => onBreakfastToggle(room.id-1)} />  
            </View>
          </View>
          <View style={styles.foodInfoContainer}>
            <View style={styles.textContainer}>
              <Text style={styles.optionsText}>Lunch</Text>
            </View>
            <View style={styles.checkboxContainer}>
              <Text style={styles.optionsText}>(+${foodOptions.lunchPrice})</Text>
              <Checkbox 
                style={styles.checkboxStyle}
                value={room.hasLunch}
                color= "#131155"
                onValueChange={() => onLunchToggle(room.id-1)} />  
            </View>
          </View>
          <View style={styles.foodInfoContainer}>
            <View style={styles.textContainer}>
              <Text style={styles.optionsText}>Dinner</Text>
            </View>
            <View style={styles.checkboxContainer}>
              <Text style={styles.optionsText}>(+${foodOptions.dinnerPrice})</Text>
              <Checkbox 
                style={styles.checkboxStyle}
                value={room.hasDinner}
                color= "#131155"
                onValueChange={() => onDinnerToggle(room.id-1)} />  
            </View>
          </View>
        </View>
      </Collapsible>
    </View>
  );
};


const styles = StyleSheet.create({
    
    roomTitle: {
      fontSize: 18,
      fontWeight: "bold",
      color: "#131155",
    },
   
    foodInfoContainer: {
      flexDirection: 'row',
      justifyContent: 'space-between',
      alignItems: 'center',
      padding:4,
  
    },
    optionsText: {
      fontSize: 18,
      color: "#131155",
    },
    container:{
      backgroundColor: "#E0E5FF", // Background color to make the shadow more visible
      borderRadius: 10,
      shadowColor: '#000',
      shadowOffset: { width: 0, height: 4 },
      shadowOpacity: 0.3,
      shadowRadius: 4,
      elevation: 5, // for Android
      padding: 10,
    },
  
    textContainer: {
      flexDirection: 'row',
      alignItems: 'center',
    },
    checkboxContainer: {
      flexDirection: 'row',
      alignItems: 'center',
    },
    horizontalLine: {
      borderBottomColor: "#131155",
      borderBottomWidth: 1,
      marginVertical: 8,
    },
    additionalOptionsLabel: {
      fontSize: 16,
      fontWeight: "bold",
      color: "#131155",
      marginBottom: 8,
      
    },
    
    checkboxStyle: {
      marginLeft:10,
    },
 
  });
  
export default AdditionalOptionsCollapse;
