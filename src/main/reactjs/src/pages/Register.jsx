import React, { useReducer } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import EmailVerification from '../components/EmailVerification';
import '../assets/button.css';
import registerStyle from '../assets/register.module.css';
import { initialState, reducer } from '../reducers/RegisterReducers';
import Alert from '../components/Alert';
import useAlert from '../hooks/useAlert';

const Register = () => {

    // useReducer 훅을 사용하여 모든 입력란에 대한 상태 관리
    const [state, dispatch] = useReducer(reducer, initialState);

    /** 
     * 동적 Handler Function
     *  */
    function handleChange(e) {

        let { name, value } = e.target;
        value = e.target.value.replace(/\s+/g, ''); //공백제거

        //입력시 전화번호,생년월일은 숫자만 입력되도록 하고, reducer를 통해 value값을 동적으로 업데이트 시켜준다.
        switch (name) {
            case 'userName':
                dispatch({ type: 'SET_FIELD', field: name, value: value.replace(/[0-9]/g, '') })
                break;
            case 'phone':
                dispatch({ type: 'SET_FIELD', field: name, value: value.replace(/[^0-9]/g, '') })
                break;
            case 'birth':
                dispatch({ type: 'SET_FIELD', field: name, value: value.replace(/[^0-9]/g, '') })
                break;
            default:
                dispatch({ type: 'SET_FIELD', field: name, value }) //field: name , value: value -> key,value 형식 축약
        }

    }

    function handleBlur(e) {
        const { name, value } = e.target;
        //validation 검사
        dispatch({ type: 'SET_ERROR', field: name, value })
    }

    //EmailVerification컴포넌트 받아온 값 세팅
    function setEmail(email) {
        dispatch({ type: 'SET_FIELD', field: 'email', value: email })
    }

    const navigate = useNavigate();

     // useAlert 커스텀 훅 사용해서 custom alert
     const { showAlert, showAlertMessage, alertMessage, closeAlert } = useAlert();

    /** 
     * API Fuction
     *  */

    function handleOnSubmit(e) {
        e.preventDefault();

        const reqData = {
            "userId": state.userId,
            "pw": state.password,
            "name": state.userName,
            "phoneNumber": state.phone,
            "email": state.email,
            "birth": state.birth
        };

        console.log(reqData);

        //객체내 모든값이 존재하는지에 대한 문법(객체 value -> 배열로 만들어줌)
        if (Object.values(reqData).every(value => value)) {

            axios.post(
                '/api/register', reqData,
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
        } else {
            showAlertMessage('모든 입력란은 필수입니다.');
        }
    }


    function handleCancel(e) {
        e.preventDefault();
        navigate('/');
    }

    return (
        <div className={registerStyle.register_page}>
            <div className={registerStyle.container}>
                <div className={registerStyle.titleWrap}>
                    회원가입
                </div>
                <div className={registerStyle.noticeLabel}>
                    <label> * 모든 입력란은 필수입니다. </label>
                </div>
                <form className={registerStyle.contentWrap} onSubmit={handleOnSubmit}>
                    {/*아이디 입력 필드*/}
                    <div className={registerStyle.idContent}>
                        <label>아이디</label>
                        <input
                            className={registerStyle.idInputWrap}
                            id='idRegister'
                            name='userId'
                            value={state.userId}    /** state에서 userId 값을 가져옴 */
                            type='text'
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {state.userIdError && <div className="error">{state.userIdError}</div>}
                    </div>

                    {/*비밀번호 입력 필드*/}
                    <div className={registerStyle.pwContent}>
                        <label>비밀번호 </label>
                        <input
                            className={registerStyle.pwInputWrap}
                            id='pwRegister'
                            name="password"
                            value={state.password}
                            autocomplete="off"
                            type='password'
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {state.passwordError && <div className={registerStyle.error}>{state.passwordError}</div>}
                    </div>


                    {/*이름 입력 필드*/}
                    <div className={registerStyle.nameContent}>
                        <label>이름 </label>
                        <input
                            className={registerStyle.nameInputWrap}
                            id='userName'
                            autocomplete="off"
                            name='userName'
                            value={state.userName}
                            type='text'
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {state.userNameError && <div className={registerStyle.error}>{state.userNameError}</div>}
                    </div>


                    {/*전화번호 입력 필드*/}
                    <div className={registerStyle.phoneContent}>
                        <label>전화번호 </label>
                        <input
                            className={registerStyle.phoneInputWrap}
                            id='phone'
                            autocomplete="off"
                            name='phone'
                            value={state.phone}
                            type='text'
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {state.phoneError && <div className={registerStyle.error}>{state.phoneError}</div>}
                    </div>

                    {/*생년월일 입력 필드*/}
                    <div className={registerStyle.birthContent}>
                        <label>생년월일(6자) </label>
                        <input
                            className={registerStyle.birthInputWrap}
                            id='birth'
                            autocomplete="off"
                            name='birth'
                            value={state.birth}
                            type='text'
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {state.birthError && <div className={registerStyle.error}>{state.birthError}</div>}
                    </div>
                    <EmailVerification setEmail={setEmail} />
                </form>
                <div className='btnWrap'>
                    {/*가입 버튼*/}
                    <button type='submit' className='submitBtn' onClick={handleOnSubmit}>가입</button>
                    {/*취소 버튼*/}
                    <button type='button' className='cancelBtn' onClick={handleCancel}>취소</button>
                </div>
            </div>
            {showAlert && <Alert message={alertMessage} onClose={closeAlert}/>}
        </div>
    );
}

export default Register;