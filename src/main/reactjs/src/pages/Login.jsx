import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import loginStyle from '../assets/login.module.css';
import Alert from '../components/Alert';
import useAlert from '../hooks/useAlert';

const Login = () => {

    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');
    const [idError, setIdError] = useState('');
    const [passwordError, setPasswordError] = useState('');

    // useNavigate 훅을 호출하여 페이지 이동시킬 navigate 함수 사용
    const navigate = useNavigate();

    // useAlert 커스텀 훅 사용해서 custom alert
    const { showAlert, showAlertMessage, alertMessage, closeAlert } = useAlert();

    /** 
     * 동적 Handler Function
     *  */
    //입력값에 공백이 들어오지 못하게 막음
    function handleInputChange(e, setterFunction) {
        e.target.value = e.target.value.replace(/\s+/g, '');
        setterFunction(e.target.value);
    }

    function handleIdChange(e) {
        setIdError('');
        handleInputChange(e, setUserId);
    }

    function handlePasswordChange(e) {
        setPasswordError('');
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
            showAlertMessage("아이디/비밀번호를 입력해주세요.");
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
        <div className={loginStyle.background_page}>
            <div className={loginStyle.container}>
                <div className={loginStyle.grid_item}>PLANIT</div>
                <form className={loginStyle.grid_item} onSubmit={handleSubmit}>
                    {/*아이디 입력 필드*/}
                    <div className='contentWrap'>
                        <input
                            placeholder='아이디'
                            className={loginStyle.idInputWrap}
                            id='id'
                            name='userId'
                            value={userId}
                            onChange={handleIdChange}
                            onBlur={handleValidation}
                            type='text'
                        />
                        {idError && <div className={loginStyle.idError}>{idError}</div>}

                        {/*비밀번호 입력 필드*/}
                        <input
                            placeholder='비밀번호'
                            className={loginStyle.pwInputWrap}
                            id='password'
                            name='password'
                            value={password}
                            autoComplete="off"
                            onChange={handlePasswordChange}
                            onBlur={handleValidation}
                            type='password'
                        />
                        {passwordError && <div className={loginStyle.passwordError}>{passwordError}</div>}
                    </div>
                    <div className={loginStyle.buttonWrap}>
                        {/*로그인 버튼*/}
                        <button className={loginStyle.loginBtn} type='submit'>로그인</button>
                    </div>
                </form>
                <div className={loginStyle.grid_item}>
                    <a href='/findid' onClick={handleFindIdPopup}>아이디</a>
                    <label> | </label>
                    <a href='/findPw' onClick={handleFindPwPopup}>비밀번호</a>
                    <label> 찾기</label>
                </div>
                <div className={loginStyle.grid_item}>
                    <button className={loginStyle.linkbtn} onClick={handleRegister}>회원가입</button>
                </div>
                {showAlert && <Alert message={alertMessage} onClose={closeAlert} />}
            </div>
        </div>

    );
};

export default Login;