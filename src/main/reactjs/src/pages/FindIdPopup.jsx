import React from 'react';

/**
 * 아이디찾기 :이름 생년월일 Email인증 필요
 * @returns 아이디찾기결과값 (page이동)
 */
const FindIdPopup = () => {
    return (
        <div>
            {/* <div className='nameWrap'>
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
            </div> */}

        </div>
    );
};

export default FindIdPopup;