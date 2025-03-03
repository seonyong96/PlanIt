import React, { useReducer } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import EmailVerification from '../components/EmailVerification';
import '../assets/button.css'
import { initialState,reducer } from '../reducers/RegisterReducers';

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
        }else {
            alert('모든 입력란은 필수입니다.');
        }
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
                        <label style={{ color: "red" }}> * 모든 입력란은 필수입니다. </label>
                    </div>
                    {/*아이디 입력 필드*/}
                    <div className='idRegisterWrap'>
                        <label>아이디 </label>
                        <input
                            className='idRegisterWrap'
                            id='idRegiste'
                            name='userId'
                            value={state.userId}    /** state에서 userId 값을 가져옴 */
                            type='text'
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {state.userIdError && <div className="error">{state.userIdError}</div>}
                    </div>

                    {/*비밀번호 입력 필드*/}
                    <div className='pwRegisterWrap'>
                        <label>비밀번호 </label>
                        <input
                            className='pwRegisterWrap'
                            id='pwRegister'
                            name="password"
                            value={state.password}
                            autocomplete="off"
                            type='password'
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {state.passwordError && <div className="error">{state.passwordError}</div>}
                    </div>


                    {/*이름 입력 필드*/}
                    <div className='nameWrap'>
                        <label>이름 </label>
                        <input
                            className='nameInputWrap'
                            id='userName'
                            autocomplete="off"
                            name='userName'
                            value={state.userName}
                            type='text'
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {state.userNameError && <div className="error">{state.userNameError}</div>}
                    </div>


                    {/*전화번호 입력 필드*/}
                    <div className='phoneWrap'>
                        <label>전화번호 </label>
                        <input
                            className='phoneInputWrap'
                            id='phone'
                            autocomplete="off"
                            name='phone'
                            value={state.phone}
                            type='text'
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {state.phoneError && <div className="error">{state.phoneError}</div>}
                    </div>

                    {/*생년월일 입력 필드*/}
                    <div className='birthWrap'>
                        <label>생년월일(6자) </label>
                        <input
                            className='birthInputWrap'
                            id='birth'
                            autocomplete="off"
                            name='birth'
                            value={state.birth}
                            type='text'
                            onChange={handleChange}
                            onBlur={handleBlur}
                        />
                        {state.birthError && <div className="error">{state.birthError}</div>}
                    </div>

                    <EmailVerification setEmail={setEmail} />
                </div>
                
                <div className='btnWrap'>
                    {/*가입 버튼*/}
                    <button type='submit' className='submitBtn'>가입</button>
                    {/*취소 버튼*/}
                    <button type='button' className='cancelBtn' onClick={handleCancel}>취소</button>
                </div>

            </form>
        </div>
    );
}

export default Register;