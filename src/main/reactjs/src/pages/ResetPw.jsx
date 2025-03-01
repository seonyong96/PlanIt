import axios from 'axios';
import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';

const ResetPw = () => {

    const location = useLocation();
    const { result } = location.state || {};

    const [resetPassword, setResetPassword] = useState('');
    const [checkResetPassword, setCheckResetPassword] = useState('');
    const [resetPasswordError, setResetPasswordError] = useState('');
    const [checkResetPasswordError, setCheckResetPasswordError] = useState('');
    const [passwordMatchFlag, setPasswordMatchFlag] = useState(false);

    function handleInputChange(e, setterFunction) {
        let { value } = e.target;

        value = value.replace(/\s+/g, '');
        setterFunction(value);

    }

    function handleChangeResetPassword(e) {
        handleInputChange(e, setResetPassword)
    }

    function handleChangeCheckResetPassword(e) {

        handleInputChange(e, setCheckResetPassword);

        if (resetPassword !== e.target.value) {
            setCheckResetPasswordError('새 비밀번호와 일치하지 않습니다.');
            setPasswordMatchFlag(false);
        } else if (resetPassword === e.target.value) {
            setCheckResetPasswordError('새 비밀번호와 일치합니다.');
            setPasswordMatchFlag(true);
        }

    }

    function handleValidation(e) {
        const { name, value } = e.target;

        console.log(name, value)

        switch (name) {
            case 'resetPassword':
                if (!value) {
                    setResetPasswordError('새 비밀번호를 입력해주세요.');
                } else if (!/^[a-zA-Z0-9]+$/.test(value)) {
                    setResetPasswordError('새 비밀번호는 영문,숫자만 입력해주세요.');
                } else { setResetPasswordError(''); }
                break;
            case 'checkResetPassword':
                if (!value) {
                    setCheckResetPasswordError('새 비밀번호와 일치하지 않습니다.');
                    setPasswordMatchFlag(false);
                } else if (value === resetPassword) {
                    setCheckResetPasswordError('새 비밀번호와 일치합니다.');
                    setPasswordMatchFlag(true);
                } else {
                    setCheckResetPasswordError('새 비밀번호와 일치하지 않습니다.');
                    setPasswordMatchFlag(false);
                }
                break;
        }
    }

    function handleResetSubmit(e) {

        const reqData = {
            "userId": result.userId,
            "name": result.userName,
            "email": result.email,
            "birth": result.birth,
            "newPw": resetPassword,
            "NewPwCheck": checkResetPassword
        }

        if (Object.values(reqData).every(value => value)) {

            axios.post(
                '/api/setNewPw', reqData,
                {
                    headers: {
                        'Content-type': 'application/json',
                        'Accept': 'application/json'
                    }
                }).then(res => {
                    console.log(res);
                    alert('이건 비밀번호 변경이야')
                    if (res.status === '200') {
                        alert('비밀번호가 변경되었습니다.');
                        window.close();
                    }
                }).catch(err => {
                    alert(err.response.data.message);
                })
            }else{
                alert('모든 입력란은 필수입니다.');
            }

    }

    function handleCancel(e) {
        e.preventDefault();
        window.close();
    }


    return (
        <div>
            <h1>비밀번호 변경</h1>
            <div className='resetPasswordContentWrap'>
                <label>새 비밀번호 </label>
                <input
                    className='resetPasswordWrap'
                    id='resetPassword'
                    name="resetPassword"
                    value={resetPassword}
                    autocomplete="off"
                    type='password'
                    onChange={handleChangeResetPassword}
                    onBlur={handleValidation}
                />
                {resetPasswordError && <div className="error">{resetPasswordError}</div>}

                <label>새 비밀번호 확인</label>
                <input
                    className='checkResetPasswordWrap'
                    id='checkResetPassword'
                    name="checkResetPassword"
                    value={checkResetPassword}
                    autocomplete="off"
                    type='password'
                    onChange={handleChangeCheckResetPassword}
                    onBlur={handleValidation}
                />
                {passwordMatchFlag ?
                    <div style={{ color: 'blue' }}>{checkResetPasswordError}</div>
                    :
                    <div className="error" style={{ color: 'red' }}>{checkResetPasswordError}</div>
                }
            </div>
            <div className='btnWrap'>
                {/*변경 버튼*/}
                <button type='button' className='submitBtn' onClick={handleResetSubmit}>변경</button>
                {/*취소 버튼*/}
                <button type='button' className='cancelBtn' onClick={handleCancel}>취소</button>
            </div>
        </div>
    );
};

export default ResetPw;