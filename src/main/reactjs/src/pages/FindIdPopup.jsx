import React, { useState } from 'react';
import EmailVerification from '../components/EmailVerification';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../assets/button.css'

/**
 * 아이디찾기 :이름 생년월일 Email인증 필요
 * @returns 아이디찾기결과값 (page이동)
 */
const FindIdPopup = () => {
    const [userName, setUserName] = useState('');
    const [userNameError, setUserNameError] = useState('');
    const [birth, setBirth] = useState('');
    const [birthError, setBirthError] = useState('');
    const [email, setEmail] = useState(''); //백엔드에 email값을 넘겨줘야함

    const navitgate = useNavigate();

    //입력값에 공백이 들어오지 못하게 막음
    function handleInputChange(e, setterFunction) {
        let { name, value } = e.target;

        value = value.replace(/\s+/g, '');

        //생년월일 일때는 숫자만입력가능, 이름은 문자만 입력가능
        if (name === 'birth') {
            value = value.replace(/[^0-9]/g, '');
            setterFunction(value);
        } else if (name === 'userName') {
            value = value.replace(/[0-9]/g, '');
            setterFunction(value);
        } else {
            setterFunction(value);
        }
    }

    function handleUserNameChange(e) {
        handleInputChange(e, setUserName);
    }

    function handleBirthChange(e) {
        handleInputChange(e, setBirth);
    }

    function handleValidation(e) {
        const { name, value } = e.target;

        console.log(name, value)

        switch (name) {
            case 'userName':
                if (!value) {
                    setUserNameError('이름을 입력해주세요.');
                } else {
                    setUserNameError('');
                }
                break;
            case 'birth':
                if (!value) {
                    setBirthError('생년월일 6자리를 입력해주세요.');
                } else if (!/^\d{6}$/.test(value)) {
                    setBirthError('생년월일 6자리를 입력해주세요.');
                } else {
                    setBirthError('');
                }
                break;
        }
    }

    function handleFindSubmit(e) {
        const reqData = {
            "name": userName,
            "email": email,
            "birth": birth
        }

        if (Object.values(reqData).every(value => value)) {

            axios.post('/api/userIdSearch', reqData, {
                headers: {
                    'Content-type': 'application/json',
                    'Accept': 'application/json'
                }
            }).then(res => {
                alert('이건 아이디찾기 성공이야');
                console.log(res);

                // navigate로 res 데이터를 state에 담아서 전달 (result는 key)
                navitgate('/findidresult', { state: { result: res.data } });

            }).catch(err => {
                alert(err.response.data.message);
            })

        } else {
            alert('모든 입력란은 필수입니다.');
        }
    }

    function handleCancel(e) {
        window.close();
    }

    return (
        <div>
            <div className='findIdContentWrap'>
                <div className='nameWrap'>
                    <label>이름 </label>
                    <input
                        className='nameInputWrap'
                        id='name'
                        autocomplete="off"
                        name='userName'
                        value={userName}
                        type='text'
                        onChange={handleUserNameChange}
                        onBlur={handleValidation}
                    />
                    {userNameError && <div className="error">{userNameError}</div>}
                </div>
                <div className='birthWrap'>
                    <label>생년월일(6자) </label>
                    <input
                        className='birthInputWrap'
                        id='birth'
                        autocomplete="off"
                        name='birth'
                        value={birth}
                        type='text'
                        onChange={handleBirthChange}
                        onBlur={handleValidation}
                    />
                    {birthError && <div className="error">{birthError}</div>}
                </div>
                <EmailVerification setEmail={setEmail} />
            </div>
            <div className='btnWrap'>
                {/*아이디찾기 버튼*/}
                <button type='button' className='submitBtn' onClick={handleFindSubmit}>찾기</button>
                {/*취소 버튼*/}
                <button type='button' className='cancelBtn' onClick={handleCancel}>취소</button>
            </div>
        </div>
    );
};

export default FindIdPopup;