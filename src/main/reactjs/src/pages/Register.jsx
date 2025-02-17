import React, { useState } from 'react';
import {useNavigate} from 'react-router-dom';
import axios from "axios";

const Register = () => {

//입력 칸 동적 value담기
const [id, setId] = useState('');
const [password, setPassword] = useState('');
const [name, setName] = useState('');
const [phone, setPhone] = useState('');
const [birth, setBirth] = useState('');
const [email, setEmail] = useState('');
const [emailVerify, setEmailVerify] = useState('');

const handleIdChange = (e) => setId(e.target.value);
const handlePasswordChange = (e) => setPassword(e.target.value);
const handleNameChange = (e) => setName(e.target.value);
const handlePhoneChange = (e) => setPhone(e.target.value);
const handleBirthChange = (e) => setBirth(e.target.value);
const handleEmailChange = (e) => setEmail(e.target.value);
const handleEmailVerifyChange = (e) => setEmailVerify(e.target.value);

const navigate = useNavigate();

function handleSendEmail(e){
    e.preventDefault();

    const emailTest = {
        "email" : "ezz1502@naver.com",
    }

    axios.post(
        //TODO : 경로확인
        '/api/sendMail',emailTest,
        {
            headers: {
                'Content-Type':'application/json',
                'Accept' : 'application/json'
            }
        }).then(res => {
            alert('인증요청이야');
            console.log(res.data);
        }).catch(err => {
            console.log(err);
        })
}

function handleVerifyEmail(e){
    e.preventDefault();

    const emailTest = {
        "email" : "ezz1502@naver.com",
        "verifyNum" : "******"
    }

    axios.post(
        //TODO : 경로확인
        '/api/sendMailVerify',emailTest,
        {
            headers: {
                'Content-Type':'application/json',
                'Accept' : 'application/json'
            }
        }).then(res => {
            alert('인증확인이야');
            console.log(res.data);
        }).catch(err => {
            console.log(err);
        })
}

function handleOnSubmit(e) {
    e.preventDefault();

    const test = {
                "userId" : "tesw123",
                "pw" : "test",
                "name" : "lizzy",
                "phoneNumber" : "01000000000",
                "email" : "test@gmail.com",
                "birth" : "070717"
                };

    axios.post(
        '/api/register', test , 
        {
            headers: {
                'Content-type': 'application/json',
                'Accept': 'application/json'
            }
        }).then(res => {
            alert('이건 회원가입이야');
            console.log(res.data);
            navigate('/');
        }).catch(err => {
            alert(err.response.data.message);
            
    })
}


function handleCancel(e) {
    e.preventDefault();
    navigate('/');
}


    return (
        <div>
            <form onSubmit={handleOnSubmit}>
            <div className='registerContentWrap'>
                <div>
                    <label style={{color:"red"}}> * 모든 입력란은 필수입니다. </label>
                </div>
                    {/*아이디 입력 필드*/}
                    <div className='idRegisterWrap'>
                        <label>아이디 </label>
                        <input
                            className='idRegisterWrap'
                            id='idRegiste'
                            value={id}
                            type='text'
                            onChange={handleIdChange}
                        />
                    </div>

                    {/*비밀번호 입력 필드*/}
                    <div className='pwRegisterWrap'>
                        <label>비밀번호 </label>
                        <input
                            className='pwRegisterWrap'
                            id='pwRegister'
                            value={password}
                            autocomplete="off"
                            type='password'
                            onChange={handlePasswordChange}
                        />
                    </div>

                    
                    {/*이름 입력 필드*/}
                    <div className='nameWrap'>
                        <label>이름 </label>
                        <input
                            className='nameInputWrap'
                            id='name'
                            autocomplete="off"
                            value={name}
                            type='text'
                            onChange={handleNameChange}
                        />
                    </div>

                    
                    {/*전화번호 입력 필드*/}
                    <div className='phoneWrap'>
                        <label>전화번호 </label>
                        <input
                            className='phoneInputWrap'
                            id='phone'
                            autocomplete="off"
                            value={phone}
                            type='text'
                            onChange={handlePhoneChange}
                        />
                    </div>
                    
                    {/*생년월일 입력 필드*/}
                    <div className='birthWrap'>
                        <label>생년월일(6자) </label>
                        <input
                            className='birthInputWrap'
                            id='birth'
                            autocomplete="off"
                            value={birth}
                            type='text'
                            onChange={handleBirthChange}
                        />
                    </div>

                    
                    {/*E-Mail 입력 필드*/}
                    <div className='emailWrap'>
                        <label>E-Mail </label>
                        <input
                            className='emailInputWrap'
                            id='email'
                            value={email}
                            autocomplete="off"
                            type='text'
                            onChange={handleEmailChange}
                        />

                        <button type='button' className='emailAuthBtn' onClick={handleSendEmail}>인증요청</button>

                        <input
                            className='emailVerifyInputWrap'
                            id='emailVerify'
                            autocomplete="off"
                            value={emailVerify}
                            type='text'
                            onChange={handleEmailVerifyChange}
                        />
                        <button type='button' className='emailVerifyBtn' onClick={handleVerifyEmail}>확인</button>
                    </div>
                    
                </div>
                <div className='btnWrap'>
                    {/*가입 버튼*/}
                    <button type='submit' className='registerBtn'>가입</button>
                    {/*취소 버튼*/}
                    <button type='button' className='cancelBtn' onClick={handleCancel}>취소</button>
                </div>

            </form>
        </div>
    );
}

export default Register;