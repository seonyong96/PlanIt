import React from 'react';
import '../assets/alert.css'

const Alert = ({ message, onClose }) => {
    return (
        <div className='alert-overlay'>
            <div className="alert-box">
                <p>{message}</p>
                <button onClick={onClose} className='close-btn'>확인</button>
            </div>

        </div>
    );
};

export default Alert;