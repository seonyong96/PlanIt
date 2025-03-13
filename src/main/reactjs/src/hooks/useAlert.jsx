import React, { useState } from 'react';

const useAlert = () => {
    //alert관련 state
    const [showAlert, setShowAlert] = useState(false);
    const [alertMessage, setAlertMessage] = useState('');

    //Custom Alert창을 위한 Function
    const showAlertMessage = (alertMessage) => {
        setAlertMessage(alertMessage);
        setShowAlert(true);
    }

    const closeAlert = () => {
        setShowAlert(false);
    }

    return ({
        showAlert,
        alertMessage,
        showAlertMessage,
        closeAlert
    });
};

export default useAlert;