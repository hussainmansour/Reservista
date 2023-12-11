import React from 'react';
import { View, Text, Modal, StyleSheet, ScrollView } from 'react-native';
import { Formik } from 'formik';
import * as Yup from 'yup';
import CustomTextInput from '../Components/EditTextInput';
import CustomizedButton from '../Components/CustomizedButton';
import DropdownList from '../Components/DropdownList';
import styles from '../Styles/Editstyles';

const validationSchema = Yup.object().shape({
    firstName: Yup.string().required('First Name is required'),
    lastName: Yup.string().required('Last Name is required'),
    birthDate: Yup.string().required('Date of birth is required')
});


const ProfileEditScreen = ({ isVisible, onSave, onCancel, user }) => {
    return (
        <Modal visible={isVisible} animationType="slide" transparent={true}>
            <View
                style={styles.modalContainer}>
                <View style={styles.modalContent}>
                    <Text style={styles.modalTitle}>Edit Profile</Text>

                    <Formik
                        initialValues={user}
                        validationSchema={validationSchema}
                        onSubmit={onSave}
                    >
                        {({ handleChange, handleBlur, handleSubmit, values, errors }) => (
                            <ScrollView>

                                <CustomTextInput
                                    title="First Name"
                                    onChangeText={handleChange('firstName')}
                                    onBlur={handleBlur('firstName')}
                                    errorMessage={errors.firstName}
                                    Value={values.firstName}
                                />

                                <CustomTextInput
                                    title="Middle Name"
                                    onChangeText={handleChange('middleName')}
                                    onBlur={handleBlur('middleName')}
                                    errorMessage={errors.middleName}
                                    Value={values.middleName}
                                />

                                <CustomTextInput
                                    title="Last Name"
                                    onChangeText={handleChange('lastName')}
                                    onBlur={handleBlur('lastName')}
                                    errorMessage={errors.lastName}
                                    Value={values.lastName}
                                />

                                <CustomTextInput
                                    title="'Date of birth"
                                    onChangeText={handleChange('birthDate')}
                                    onBlur={handleBlur('birthDate')}
                                    errorMessage={errors.birthDate}
                                    Value={values.birthDate}
                                />

                                <DropdownList
                                    label="Gender"
                                    selectedValue={values.gender}
                                    onValueChange={(itemValue) => handleChange('gender')(itemValue)}
                                    onBlur={() => handleBlur('gender')}
                                    items={[
                                        { label: 'Male', value: 'Male' },
                                        { label: 'Female', value: 'Female' },
                                        { label: 'Prefer not to say', value: 'Prefer not to say' },
                                    ]}
                                />

                                <DropdownList
                                    label="Nationality"
                                    selectedValue={values.nationality}
                                    onValueChange={(itemValue) => handleChange('nationality')(itemValue)}
                                    onBlur={() => handleBlur('nationality')}
                                    items={[
                                        { label: 'Egypt', value: 'EG' },
                                        // ... (other nationality options on the connection)
                                    ]}
                                />

                                <View style={styles.buttonContainer}>
                                    {/* Save Button */}
                                    <CustomizedButton
                                        onPress={handleSubmit}
                                        buttonStyle={styles.saveButton}
                                        textStyle={styles.buttonText}
                                        text="Save"
                                    />

                                    {/* Cancel Button */}
                                    <CustomizedButton
                                        onPress={onCancel}
                                        buttonStyle={styles.cancelButton}
                                        textStyle={styles.buttonText}
                                        text="Cancel"
                                    />
                                </View>
                            </ScrollView>
                        )}
                    </Formik>
                </View>
            </View>
        </Modal>
    );
};



export default ProfileEditScreen;
