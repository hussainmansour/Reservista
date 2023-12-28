import {Alert, Modal, ScrollView, StyleSheet, Text, View} from "react-native";
import styles from "../../Styles/Editstyles";
import {Formik} from "formik";
import CustomTextInput from "../Inputs/EditTextInput";
import CustomizedButton from "../General/Buttons/CustomizedButton";
import React, {useState} from "react";
import {Layout, Button, useStyleSheet, Datepicker} from '@ui-kitten/components';
import * as Yup from "yup";
import Color from "../../Styles/Color";


// for validation
const validationSchema = Yup.object().shape({
    voucherCode: Yup.string()
        .required('Voucher Name is required')
        .min(4, 'Voucher Name must be at least 4 characters')
        .max(50, 'Voucher Name must be at most 50 characters'),
    discountRate: Yup.number()
        .required('Voucher percentage is required')
        .min(1, 'Percentage must be greater than 0')
        .max(100, 'Percentage must be less than or equal to 100'),
});

const AddVoucherModel = ({isVisible, onCancel, onSave}) => {


    const [expirationDate, setExpirationDate] = useState(null);
    const today = new Date();
    const nextYear = new Date(today.getFullYear() + 5, today.getMonth(), today.getDate());
    return (
        <Modal visible={isVisible} animationType="slide" transparent={false}>
            <View style={{backgroundColor: Color.PALEBLUE, flex: 1, justifyContent: "center", paddingTop: 50}}>
                <Text style={styles.modalTitle}>Add Voucher</Text>

                <Formik
                    initialValues={{
                        voucherCode: '',
                        discountRate: 0,
                        expiresAt: null,
                    }}
                    validationSchema={validationSchema}
                    onSubmit={onSave}
                >
                    {({handleChange, handleBlur, values, errors, isValid, touched}) => (
                        <View style={{flex: 1}}>
                            <CustomTextInput
                                title="Voucher Name"
                                onChangeText={handleChange('voucherCode')}
                                onBlur={handleBlur('voucherCode')}
                                errorMessage={errors.voucherCode}
                                Value={values.voucherCode}
                                textStyle={{color: Color.MIDNIGHTBLUE}}
                                textInputStyle={{backgroundColor: Color.MIDNIGHTBLUE}}
                            />
                            <CustomTextInput
                                title="Voucher Percentage"
                                onChangeText={handleChange('discountRate')}
                                onBlur={handleBlur('discountRate')}
                                errorMessage={errors.discountRate}
                                Value={values.discountRate}
                                keyboardType='numeric'
                            />
                            <View style={{marginBottom: 15, marginLeft: 21, width: 300}}>
                                <Text style={{
                                    color: Color.MIDNIGHTBLUE,
                                    fontWeight: 'bold',
                                    marginBottom: 5,
                                    paddingLeft: 10,
                                    fontSize: 15,
                                }}>
                                    Expiration Date
                                </Text>
                                <Datepicker
                                    placeholder="Select Date"
                                    date={expirationDate}
                                    onSelect={(date) => {
                                        setExpirationDate(date);
                                        values.expiresAt = date;
                                    }}
                                    controlStyle={{backgroundColor: Color.PALEBLUE, borderColor: Color.SEABLUE}}
                                    min={today}
                                    max={nextYear}

                                />
                                {expirationDate == null && (
                                    <Text style={{color: 'red'}}>Please select a date before proceeding</Text>
                                )}
                                {expirationDate != null && (<Text>
                                        Selected
                                        Date: {expirationDate ? expirationDate.toDateString() : 'No date selected'}
                                    </Text>
                                )}

                            </View>
                            <View style={styles.buttonContainer}>

                                <CustomizedButton
                                    onPress={() => {
                                        onCancel();
                                        setExpirationDate(null)
                                    }}
                                    buttonStyle={{backgroundColor: Color.ORANGE}}
                                    textStyle={styles.buttonText}
                                    text="Cancel"

                                />

                                <CustomizedButton
                                    onPress={() => {
                                        onSave(values)
                                    }}
                                    buttonStyle={{
                                        backgroundColor: Color.SEABLUE,
                                        opacity: (!isValid || expirationDate == null || !touched.voucherCode) ? 0.5 : 1
                                    }}
                                    textStyle={styles.buttonText}
                                    text="Save"
                                    isDisabled={!isValid || !touched.voucherCode || expirationDate == null}
                                />
                            </View>
                        </View>
                    )}
                </Formik>
            </View>
        </Modal>
    );
}
const style = StyleSheet.create({});
export default AddVoucherModel;
