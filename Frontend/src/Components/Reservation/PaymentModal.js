import {
  StripeProvider,
  CardField,
  useConfirmPayment,
} from "@stripe/stripe-react-native";
import React, { useState, useEffect} from "react";
import {
  Modal,
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  Button,
  Alert,
} from "react-native";
import Color from "../../Styles/Color.js";

const PaymentModal = ({ isVisible, onCancel, clientSecret, onSuccessfulPayment, price }) => {

  const [isReady, setIsReady] = useState(false);
  const { confirmPayment, paymentLoading } = useConfirmPayment();
  const [cardDetails, setCardDetails] = useState(null);
  const [error, setError] = useState("");

  useEffect(() => {
    if (isVisible) {
      setError("");
    }
  }, [isVisible]);

  const pay = async () => {

    const { paymentIntent, error } = await confirmPayment(clientSecret, {
      paymentMethodType: "Card",
      paymentMethodData: {
        card: cardDetails, 
      },
    });

    if (error) {
      setError(error.message);
    } else if (paymentIntent) {
      setError("")
      onSuccessfulPayment();
    }
  };

  const handleCardChange = (cardDetails) => {
    if (cardDetails.complete) {
      setIsReady(true);
      setCardDetails(cardDetails);
    } else {
      setIsReady(false);
    }
    setError("");
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
                onCardChange={handleCardChange}
                style={styles.cardField}
                postalCodeEnabled={false}
                cardStyle={styles.cardStyle}
              />
              {error ? <Text style={styles.errorText}>{error}</Text> : null}
            </View>
            <Button
              title={"Pay  $" + String(price)}
              color={ Color.SEABLUE}
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
    backgroundColor:  "rgba(0, 0, 0, 0.5)",
  },
  modalContent: {
    backgroundColor: Color.PALEBLUE, // "white",
    padding: 40,
    borderRadius: 10,
    height: 310,
    width: "95%",
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
    fontSize: 20, 
    fontWeight: "600",
    marginBottom: 5, 
    color: Color.MIDNIGHTBLUE,
  },
  secondaryText: {
    fontSize: 14,
    color: Color.DARKGREY,
    marginBottom: 10, 
  },
  cardField: {
    height: 35,
    width: "100%",
    marginBottom: 1, 
  },
  cardStyle: {
    borderColor: Color.DIRTYWHITE,
    borderRadius: 1, 
    borderWidth: 1, 
  },
  ButtonStyle: {
    padding: 50,
  },
  testModeLabel: {
    backgroundColor: Color.ORANGE,
    padding: 5, 
    borderRadius: 5,
    marginBottom: 10,
    width: "27%",
  },
  testModeText: {
    color: 'white',
    fontWeight: "bold",
    fontSize: 12, 
  },
  errorText: {
    color: "red",
    fontSize: 12,
    marginTop: 1,
  },
  cardFieldContainer: {
    marginBottom: 25,
  },
});

export default PaymentModal;
