import React, { useReducer, useState } from 'react';
import '../assets/button.css'
import { initialState, reducer } from '../reducers/RegisterReducers'
import EmailVerification from '../components/EmailVerification';
import axios from 'axios';

const FindPwPopup = () => {

    const [state, dispatch] = useReducer(reducer, initialState);

    function handleChange(e) {
        let { name, value } = e.target;

        switch (name) {
            case 'userName':
                dispatch({ type: 'SET_FIELD', field: name, value: value.replace(/[0-9]/g, '') })
                break;
            case 'birth':
                dispatch({ type: 'SET_FIELD', field: name, value: value.replace(/[^0-9]/g, '') })
            default:
                dispatch({ type: 'SET_FIELD', field: name, value })
        }
    }

    function handleBlur(e) {
        let { name, value } = e.target;

        //validation 검사
        dispatch({ type: 'SET_ERROR', field: name, value })
    }

    //EmailVerification컴포넌트 받아온 값 세팅
    function setEmail(email) {
        dispatch({ type: 'SET_FIELD', field: 'email', value: email });
    }

    function handleFindSubmit(e) {
        const reqData = {
            "userId": state.userId,
            "name": state.userName,
            "email": state.email,
            "birth": state.birth
        }

        if (Object.values(reqData).every(value => value)) {

            axios.post('/api/userPwSearch', reqData, {
                headers: {
                    'Content-type': 'application/json',
                    'Accept': 'application/json'
                }
            }).then(res => {
                alert('이건 비밀번호 찾기 성공이야');
                console.log(res);

                // navigate로 res 데이터를 state에 담아서 전달 (result는 key)
                // navitgate('/findidresult', { state: { result: res.data } });

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
            <div className='findPwContentWrap'>
                <h1>비밀번호 찾기</h1>
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
                {/*찾기 버튼*/}
                <button type='button' className='submitBtn' onClick={handleFindSubmit}>비밀번호 찾기</button>
                {/*취소 버튼*/}
                <button type='button' className='cancelBtn' onClick={handleCancel}>취소</button>
            </div>
        </div>
    );
};

export default FindPwPopup;