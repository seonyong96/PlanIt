import React, { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import '../assets/login.css';

const Login = () => {
    const { register, handleSubmit, formState: { errors } } = useForm();
    
    // useNavigate 훅을 호출하여 navigate 함수 사용
    const navigate = useNavigate();

    const onSubmit = (data) => {
        alert('일단완송! 추후 api연동할 부분');
        navigate('/main');
    }


    function handlePopup() {
        const width = 800;
        const height = 400;

        //화면 중앙 위치
        const left = (window.innerWidth / 2) - (width / 2);
        const top = (window.innerHeight / 2) - (height / 2);

        //경로는 소문자로
        window.open('/findidpassword', '_blank', `width=${width},height=${height},top=${top},left=${left},resizable=no,scrollbars=no`);
    }

    return (
        <div className='loginPage'>
            <div className='titleWrap'>PLANIT</div>
            <form onSubmit={handleSubmit(onSubmit)}>
                {/*아이디 입력 필드*/}
                <div className='contentWrap'>
                    <div className='idWrap'>
                        <input
                            placeholder='아이디'
                            className='idInputWrap'
                            id='id'
                            type='text'
                            {...register('id', { required: '아이디를 입력해주세요.' })}
                        />
                        {errors.id && <p>{errors.id.message}</p>}
                    </div>

                    {/*비밀번호 입력 필드*/}
                    <div className='pwWrap'>
                        <input
                            placeholder='비밀번호'
                            className='pwInputWrap'
                            id='pw'
                            autocomplete="off"
                            type='password'
                            {...register('pw', { required: '비밀번호를 입력해주세요.' })}
                        />
                        {errors.pw && <p>{errors.pw.message}</p>}
                    </div>
                </div>

                {/*로그인 버튼*/}
                <button className='buttonWrap' type='submit'>로그인</button>

            </form>
            <div className='findWrap'>
                <button onClick={handlePopup}>아이디/비밀번호 찾기</button>
            </div>

        </div>
    );
};

export default Login;