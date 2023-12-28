import { StyleSheet } from "react-native";
import Color from "./Color";


const styles = StyleSheet.create({
    wholeForm: {
        backgroundColor: Color.PALEBLUE,
        flex: 1,
        paddingTop: 50,
        justifyContent: "flex-start",
      },
      title: {
        fontSize: 32,
        fontWeight: "bold",
        color: 'white',
        backgroundColor: Color.SEABLUE,
        paddingHorizontal: 20,
        paddingVertical: 10,
        marginBottom: 20,
        padding:10
      },
      scrollViewContainer: {
        padding: 10,
    
      },
      roomContainer: {
        borderRadius: 5,
        marginBottom: 16,
        paddingHorizontal:15,
      },
    
      voucherContainer: {
        flexDirection: "row",
        justifyContent: "space-between",
        marginBottom: 5,
        alignItems: "stretch",
        shadowColor: 'white',
        shadowOffset: {
          width: 0,
          height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
        elevation: 5,
      },
      textInputStyle: {
        flex: 2,
        backgroundColor: Color.DIRTYWHITE,
        padding: 8,
        borderRadius:0,
     
        width: 118,
        height: 43,
        alignItems: 'center',
        justifyContent: 'center',
        alignSelf: 'center',
      },
      SmallbuttonStyle: {
        backgroundColor: Color.ORANGE ,
        flex: 1,
        padding: 8,
        borderRadius: 0,
        marginBottom: 0,
        marginTop: 0,
      },
    
      buttonStyle: {
        marginBottom: 30,
        width: "95%",
        height: 50,
        alignItems: "center",
        justifyContent: "center",
        borderRadius: 5,
        backgroundColor: Color.SEABLUE,
        alignSelf: "center",
        shadowColor: "#000",
        shadowOffset: {
          width: 0,
          height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
        elevation: 5,
        marginTop:0,
      },
    
      whiteText: {
        paddingTop: 5,
        color: 'white',
        fontWeight: "bold",
        fontSize: 12,
        alignSelf: "center",
      },
    
      priceContainer: {
        padding: 16,
        marginTop: 10,
      },
    
      totalPrice: {
        fontSize: 28,
        fontWeight: "bold",
        color: Color.MIDNIGHTBLUE,
    
      },
    
      discountText: {
        fontSize: 18,
        color: Color.SEABLUE,
    
      },
    
      navyText: {
        fontSize: 22,
        fontWeight: "bold",
        color: Color.MIDNIGHTBLUE,
        marginBottom: 2,
    
      },
      fullRefundText: {
        fontSize: 18,
        fontWeight: "bold",
        color: Color.MIDNIGHTBLUE,
        marginBottom: 2,
    
      },
      calculationText:{
        fontSize: 18,
        color: Color.DARKGREY,
        marginBottom: 2,
      },
    
      applyVoucherText: {
        textDecorationLine: "underline",
        color: Color.DARKGREY,
        fontSize: 16,
        marginBottom: 5,
        marginTop: 10,
        alignSelf: "flex-end",
        marginRight: 5,
      },
      errorText: {
        color: "red",
        fontSize: 12,
        marginTop: 1,
      },
      voucherBoxContainer: {
        borderRadius: 10,
        padding: 16,
        marginBottom: 16,
        width: "95%",
        alignSelf: "center",
        borderColor: Color.SEABLUE, 
        borderWidth: 2,         
        backgroundColor: 'white', 
        shadowColor: "#000",
        shadowOffset: {
          width: 0,
          height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 3.84,
        elevation: 5,
      },
      checkboxContainer: {
        flexDirection: "row",
        justifyContent: 'flex-end',
        alignItems: 'center',
        padding: 5,
      },
      checkboxStyle: {
        marginLeft: 10,
      },
      fullyRefundableContainer: {
        flexDirection: "row",
        justifyContent: "space-between",
        alignItems: "center",
        padding: 4,
      },
      roomTitle: {
        fontSize: 18,
        fontWeight: "bold",
        color: Color.MIDNIGHTBLUE,
      },
     
      foodInfoContainer: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        padding:4,
    
      },
      optionsText: {
        fontSize: 18,
        color: Color.MIDNIGHTBLUE,
      },
      optionsText2: {
        fontSize: 18,
        color: Color.MIDNIGHTBLUE,
      },
      container:{
        backgroundColor: 'white', 
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
      checkboxContainerAdditionalOption: {
        flexDirection: 'row',
        alignItems: 'center',
      },
      horizontalLine: {
        borderBottomColor: Color.MIDNIGHTBLUE,
        borderBottomWidth: 1,
        marginVertical: 8,
      },
      additionalOptionsLabel: {
        fontSize: 16,
        fontWeight: "bold",
        color: Color.MIDNIGHTBLUE,
        marginBottom: 8,
        
      },
      
      checkboxStyle: {
        marginLeft:10,
      },
   
    });

 export default styles;