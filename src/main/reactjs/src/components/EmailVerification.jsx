import React, { useState } from 'react';
import axios from 'axios';

/**
 * 이메일 인증 컴포넌트
 */
const EmailVerification = ({setEmail}) => {

    const [email, setLocalEmail] = useState('');
    const [emailVerify, setEmailVerify] = useState('');
    const [emailError, setEmailError] = useState('');
    const [emailVerifyError, setEmailVerifyError] = useState('');

    function handleEmailChange(e){
        let { value } = e.target;

        value = value.replace(/\s+/g, '');

        setLocalEmail(value);
        setEmail(value); //부모 컴포넌트로 값 올리기
    }

    /**
     * 이메일 인증코드 요청
     * @param {*} e 
     * @returns 
     */
    function handleSendEmail(e) {
        e.preventDefault();

        if (!email) {
            setEmailError('이메일을 입력해주세요.');
        } else if (!/\S+@\S+\.\S+/.test(email)) {
            setEmailError('유효한 이메일 형식으로 입력해주세요.');
        } else {
            setEmailError(''); // 에러 메시지 비우기

            const checkRequiredEmail = { email };

            axios.post(
                //TODO : 경로확인
                '/api/mailSend', checkRequiredEmail,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json'
                    }
                }).then(res => {
                    alert('인증요청이야');
                    console.log(res.data);
                }).catch(err => {
                    console.log(err);
                })
        }

    }

    /**
     * 이메일 인증코드 확인
     * @param {*} e 
     * @returns 인증코드확인
     */
    function handleVerifyEmail(e) {
        e.preventDefault();

        if (!emailVerify) {
            setEmailVerifyError('이메일 인증번호를 입력해주세요.');
        } else {
            setEmailVerifyError(''); // 에러 메시지 비우기

            const verifyEmail = {
                email,
                "verifyNum": emailVerify
            };

            axios.post(
                //TODO : 경로확인
                '/api/mailNumberCheck', verifyEmail,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json'
                    }
                }).then(res => {
                    alert('인증확인이야');
                    console.log(res.data);
                }).catch(err => {
                    console.log(err);
                })
        }


    }
    return (
        <div>
            {/*E-Mail 입력 필드*/}
            <div className='emailWrap'>
                <label>E-Mail </label>
                <input
                    className='emailInputWrap'
                    id='email'
                    name='email'
                    value={email}
                    autoComplete="off"
                    type='text'
                    onChange={handleEmailChange}
                />
                {emailError && <div className="error">{emailError}</div>}
                <button type='button' className='emailAuthBtn' onClick={handleSendEmail}>인증요청</button>

                <input
                    className='emailVerifyInputWrap'
                    id='emailVerify'
                    autocomplete="off"
                    name='emailVerify'
                    value={emailVerify}
                    type='text'
                    onChange={(e) => setEmailVerify(e.target.value.replace((/\s+/g, '')))}
                />
                {emailVerifyError && <div className="error">{emailVerifyError}</div>}
                <button type='button' className='emailVerifyBtn' onClick={handleVerifyEmail}>확인</button>
            </div>
        </div>
    );
};

export default EmailVerification;