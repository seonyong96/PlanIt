import React,{useState,useEffect} from 'react';
import {useForm} from 'react-hook-form';
import '../assets/login.css';
import axios from 'axios';

const Login = () => {
    const {register, handleSubmit,formState:{errors}} = useForm();

    const onSubmit = (data)=> {
        alert('일단완송! 추후 api연동할 부분');
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
                            {...register('id', {required: '아이디를 입력해주세요.'})}
                        />
                        {errors.id && <p>{errors.id.message}</p>}
                    </div>
                    
                    {/*비밀번호 입력 필드*/}
                    <div className='pwWrap'>
                        <input
                            placeholder='비밀번호'
                            className='pwInputWrap'
                            id='pw'
                            type='text'
                            {...register('pw', {required: '비밀번호를 입력해주세요.'})}
                        />
                        {errors.pw && <p>{errors.pw.message}</p>}
                    </div>
                </div>
                
                {/*로그인 버튼*/}
                <button className='buttonWrap' type='submit'>로그인</button>

            </form>
        </div>
    );
};

export default Login;