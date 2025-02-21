import React, { useReducer } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import EmailVerification from '../components/EmailVerification';

//state 초기값
const initialState = {
    userId: '',
    password: '',
    userName: '',
    phone: '',
    birth: '',
    email: '',
    emailVerify: '',
    userIdError: '',
    passwordError: '',
    userNameError: '',
    phoneError: '',
    birthError: '',
    emailError: '',
    emailVerifyError: '',
}

//reducer함수 사용 , 객체로 상태관리하는 방법 action = 동적으로 업데이트 시켜줄속성? target?
function reducer(state, action) {
    switch (action.type) {
        case 'SET_FIELD':
            return {
                ...state,
                [action.field]: action.value, /** obj[state변수명] 객체의 속성명에 대한 값을 동적으로 업데이트 시켜줌 */
            }
        case 'SET_ERROR':
            return {
                ...state,
                [`${action.field}Error`]: validationField(action.field, action.value) /** obj[state변수명] 객체의 속성명에 대한 값을 동적으로 업데이트 시켜줌 */
            }
        default:
            return state;
    }
}

//각 입력 값에 대한 validation 체크부분
function validationField(field, value) {
    switch (field) {
        case 'userId':
            if (value.length === 0 || !/^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z0-9]+$/.test(value)) return 'ID는 영문+숫자 조합으로 입력해주세요.';
            return value.length > 15 ? '아이디는 15자 이내입니다.' : '';
        case 'password':
            if (value.length === 0) return '비밀번호를 입력해주세요';
            return !/^[a-zA-Z0-9]+$/.test(value) ? '비밀번호는 영문,숫자만 입력해주세요.' : '';
        case 'userName':
            return value.length === 0 ? '이름을 입력해주세요.' : '';
        case 'phone':
            if (value.length === 0) return '전화번호를 입력해주세요.';
            return !/^\d{11}$/.test(value) ? '전화번호는 11자리 숫자로 입력해주세요.' : '';
        case 'birth':
            if (value.length === 0) return '생년월일 6자리를 입력해주세요.';
            return !/^\d{6}$/.test(value) ? '생년월일은 6자리 숫자로 입력해주세요.' : '';
    }
}

const Register = () => {

    // useReducer 훅을 사용하여 모든 입력란에 대한 상태 관리
    const [state, dispatch] = useReducer(reducer, initialState);

    /** 
     * 동적 Handler Function
     *  */
    function handleChange(e) {

        const { name, value } = e.target;

        //입력시 전화번호,생년월일은 숫자만 입력되도록 하고, reducer를 통해 value값을 동적으로 업데이트 시켜준다.
        switch (name) {
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

    function setEmail(email) {
        dispatch({ type: 'SET_FIELD', field: 'email', value: email})
    }

    const navigate = useNavigate();

    /** 
     * API Fuction
     *  */

    function handleOnSubmit(e) {
        e.preventDefault();

        const userData = {
            "userId": state.userId,
            "pw": state.password,
            "name": state.userName,
            "phoneNumber": state.phone,
            "email": state.email,
            "birth": state.birth
        };

        console.log(userData);

        axios.post(
            '/api/register', userData,
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
                            id='name'
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

                    <EmailVerification setEmail={setEmail}/>

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