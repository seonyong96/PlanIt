import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../assets/login.css';

const Login = () => {

    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');
    const [idError, setIdError] = useState('');
    const [passwordError, setPasswordError] = useState('');


    // useNavigate 훅을 호출하여 페이지 이동시킬 navigate 함수 사용
    const navigate = useNavigate();

    /** 
     * 동적 Handler Function
     *  */
    //입력값에 공백이 들어오지 못하게 막음
    function handleInputChange(e, setterFunction) {
        e.target.value = e.target.value.replace(/\s+/g, '');
        setterFunction(e.target.value);
    }

    function handleIdChange(e) {
        handleInputChange(e, setUserId);
    }

    function handlePasswordChange(e) {
        handleInputChange(e, setPassword);
    }

    /**
     * validation 체크
     */
    function handleValidation(e) {
        let { name, value } = e.target;

        console.log(name, value)

        switch (name) {
            case 'userId':
                if (!value) {
                    setIdError('아이디를 입력해주세요.');
                    break;
                } else {
                    setIdError('');
                    break;
                }
            case 'password':
                if (!value) {
                    setPasswordError('비밀번호를 입력해주세요.');
                    break;
                } else {
                    setPasswordError('');
                    break;
                }
        }
    }
    /** 
     * API Fuction
     *  */
    function handleSubmit(e) {
        e.preventDefault();

        const reqData = {
            "userid": userId,
            "pw": password
        }

        if (Object.values(reqData).every(value => value)) {
            console.log('loginData : ' + reqData.userid + reqData.pw);
            /**
             * 로그인 로직 만들어지면 사용
             */
            // axios.post(
            //     '/api/login', loginData,
            //     {
            //         headers: {
            //             'Content-type': 'application/json',
            //             'Accept': 'application/json'
            //         }
            //     }).then(res => {
            //         alert('이건 로그인이야');
            //         console.log(res.data);
            //         navigate('/main');
            //     }).catch(err => {
            //         alert(err.response.data.message);
            //     })

            /**로그인 구축 전 임시 이동페이지 */
            navigate('/main');
        } else {
            alert("아이디 / 비밀번호를 입력해주세요.");
        }
    }

    /** 
     * Popup페이지 연동 Fuction
     *  */
    function handleFindIdPopup(event) {
        event.preventDefault(); // a태그일 경우 경로를 같이 써주기 때문에 기본 브라우저에서는 동작안하도록 막는 방법(팝업페이지를 위한 기능)

        const width = 800;
        const height = 400;

        //화면 중앙 위치
        const left = (window.innerWidth / 2) - (width / 2);
        const top = (window.innerHeight / 2) - (height / 2);

        //경로는 소문자로
        window.open('/findid', '_blank', `width=${width},height=${height},top=${top},left=${left},resizable=no,scrollbars=no`);
    }

    function handleFindPwPopup(event) {
        event.preventDefault();

        const width = 800;
        const height = 400;

        //화면 중앙 위치
        const left = (window.innerWidth / 2) - (width / 2);
        const top = (window.innerHeight / 2) - (height / 2);

        //경로는 소문자로
        window.open('/findPw', '_blank', `width=${width},height=${height},top=${top},left=${left},resizable=no,scrollbars=no`);
    }

    function handleRegister() {
        navigate('/register')
    }

    return (
        <div className='loginPage'>
            <div className='titleWrap'>PLANIT</div>
            <form onSubmit={handleSubmit}>
                {/*아이디 입력 필드*/}
                <div className='contentWrap'>
                    <div className='idWrap'>
                        <input
                            placeholder='아이디'
                            className='idInputWrap'
                            id='id'
                            name='userId'
                            value={userId}
                            onChange={handleIdChange}
                            onBlur={handleValidation}
                            type='text'
                        />
                        {idError && <div className='idError'>{idError}</div>}
                    </div>

                    {/*비밀번호 입력 필드*/}
                    <div className='pwWrap'>
                        <input
                            placeholder='비밀번호'
                            className='pwInputWrap'
                            id='password'
                            name='password'
                            value={password}
                            autoComplete="off"
                            onChange={handlePasswordChange}
                            onBlur={handleValidation}
                            type='password'
                        />
                        {passwordError && <div className='passwordError'>{passwordError}</div>}
                    </div>
                </div>

                {/*로그인 버튼*/}
                <button type='submit' className='buttonWrap'>로그인</button>

            </form>
            <div className='etcContent'>
                <div className='findWrap'>
                    <a href='/findid' onClick={handleFindIdPopup}>아이디</a>
                    <label> | </label>
                    <a href='/findPw' onClick={handleFindPwPopup}>비밀번호</a>
                    <label> 찾기</label>
                </div>

                <div className='registerWrap'>
                    <button onClick={handleRegister} className='linkbtn'>회원가입</button>
                </div>
            </div>

        </div>
    );
};

export default Login;