import {
  StripeProvider,
  CardField,
  useConfirmPayment,
} from "@stripe/stripe-react-native";
import React, { useState } from "react";
import {
  Modal,
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  Button,
  Alert,
} from "react-native";

const PaymentModal = ({ isVisible, onCancel, clientSecret, onSuccessfulPayment }) => {
  const [isReady, setIsReady] = useState(false);
  const { confirmPayment, paymentLoading } = useConfirmPayment();
  const [cardDetails, setCardDetails] = useState(null);
  const [error, setError] = useState("");

  const clientDummySecret =
  "pi_3OKqtPIpHzJgrvA917g0eSGN_secret_e3AbYn2WXeGumMHrap01nIANg"
  const pay = async () => {
    if (!isReady) {
      // this error should not be reached
      Alert.alert("Alert:", "Please enter your payment details");
    }
    console.log("cardDetails",cardDetails);

    const { paymentIntent, error } = await confirmPayment(clientSecret, {
      paymentMethodType: "Card",
      paymentMethodData: {
        card: cardDetails, // Use the actual variable or state holding card details
      },
    });

    if (error) {
      console.log("Payment confirmation error", error);
      setError(error.message);
    } else if (paymentIntent) {
      setError("")
      onSuccessfulPayment();
    }
  };

  return (
    <Modal
      visible={isVisible}
      transparent
      animationType="slide"
      onRequestClose={onCancel}
    >
      <View style={styles.modalContainer}>
        <StripeProvider
          publishableKey="pk_test_51O5xO9IpHzJgrvA9SXgHNyJwhzEsyPa2AEOdMmSCK0xwXt4ydWuTyU6tPnkbqVdsNNEcxoUbzEbE6R4m9SsNzw6l005nh1i9GZ"
        >
          <View style={styles.modalContent}>
            <TouchableOpacity onPress={onCancel} style={styles.closeButton}>
              <Text style={styles.closeButtonText}>x</Text>
            </TouchableOpacity>
            <View style={styles.testModeLabel}>
              <Text style={styles.testModeText}>TEST MODE</Text>
            </View>
            <Text style={styles.mainText}>Add your payment information</Text>
            <Text style={styles.secondaryText}>Card information</Text>
            <View style={styles.cardFieldContainer}>
              <CardField
                onCardChange={(cardDetails) => {
                  if (cardDetails.complete) {
                    setIsReady(true);
                    setCardDetails(cardDetails);
                    setError(""); // Clear the error message when the user starts entering card details
                  } else {
                    setIsReady(false);
                    setError("")
                  }
                }}
                style={styles.cardField}
                postalCodeEnabled={false}
                cardStyle={styles.cardStyle}
              />
              {error ? <Text style={styles.errorText}>{error}</Text> : null}
            </View>
            <Button
              title="Pay"
              style={styles.ButtonStyle}
              onPress={pay}
              disabled={paymentLoading || !isReady}
            />
          </View>
        </StripeProvider>
      </View>
    </Modal>
  );
};

const styles = StyleSheet.create({
  modalContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "rgba(0, 0, 0, 0.5)",
  },
  modalContent: {
    backgroundColor: "white",
    padding: 40,
    borderRadius: 10,
    height: 300,
    width: "95%",
    position: "relative", // Added for positioning the close button
  },
  closeButton: {
    position: "absolute",
    top: 10,
    left: 10,
    padding: 10,
    zIndex: 1,
  },
  closeButtonText: {
    fontSize: 18,
    color: "grey",
  },
  mainText: {
    fontSize: 20, // Increase the font size for a bolder look
    fontWeight: "600", // Use a heavier font weight for a bolder look
    marginBottom: 5, // Decrease the margin bottom for less space
  },
  secondaryText: {
    fontSize: 14,
    color: "grey",
    marginBottom: 10, // Decrease the margin bottom for less space
  },
  cardField: {
    height: 35,
    width: "100%",
    marginBottom: 1, // Small space between CardField and errorText
  },
  cardStyle: {
    borderColor: "#D3D3D3", // Light grey border color
    borderRadius: 1, // Adjust the border radius as needed
    borderWidth: 1, // Adjust the border width a
  },
  ButtonStyle: {
    padding: 50,
  },
  testModeLabel: {
    backgroundColor: "#FFD700",
    padding: 5, // Reduced padding
    borderRadius: 5,
    marginBottom: 10,
    width: "27%",
  },
  testModeText: {
    color: "navy", // Dark blue color
    fontWeight: "bold",
    fontSize: 12, // Smaller font size
  },
  errorText: {
    color: "red",
    fontSize: 12,
    marginTop: 1,
  },
  cardFieldContainer: {
    marginBottom: 15,
  },
});

// dark mode
// const styles = StyleSheet.create({
//   modalContainer: {
//     flex: 1,
//     justifyContent: "center",
//     alignItems: "center",
//     backgroundColor: "rgba(30, 30, 50, 0.8)", // Dark blue background for dark mode
//   },
//   modalContent: {
//     backgroundColor: "#2a2a3a", // Dark blue-grey background color
//     padding: 40,
//     borderRadius: 10,
//     height: 300,
//     width: "95%",
//     position: "relative",
//   },
//   closeButton: {
//     position: "absolute",
//     top: 10,
//     left: 10,
//     padding: 10,
//     zIndex: 1,
//   },
//   closeButtonText: {
//     fontSize: 18,
//     color: "#ccc", // Light grey text color
//   },
//   mainText: {
//     fontSize: 20,
//     fontWeight: "600",
//     marginBottom: 5,
//     color: "#fff", // White text color
//   },
//   secondaryText: {
//     fontSize: 14,
//     color: "#aaa", // Light grey text color
//     marginBottom: 10,
//   },
//   cardField: {
//     height: 35,
//     width: "100%",
//     marginBottom: 20,
//     backgroundColor: "#3a3a4a", // Dark blue-grey background color
//     borderRadius: 5,
//     padding: 10,
//   },
//   cardStyle: {
//     borderColor: "#4a4a5a", // Darker blue-grey border color
//     borderRadius: 5,
//     borderWidth: 1,
//   },
//   ButtonStyle: {
//     padding: 50,
//   },
//   testModeLabel: {
//     borderRadius: 3,
//     backgroundColor: "#ffd700", // Brighter yellowish color
//     padding: 5,
//     borderRadius: 5,
//     marginBottom: 10,
//     width: "27%",
//   },
//   testModeText: {
//     color: "#000080", // Dark blue color
//     fontWeight: "bold",
//     fontSize: 12,
//   },
// });

export default PaymentModal;
