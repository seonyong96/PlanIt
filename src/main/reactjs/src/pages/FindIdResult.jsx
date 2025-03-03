import React from 'react';
import { useLocation } from 'react-router-dom';
import '../assets/button.css'

const FindIdResult = () => {
    const location = useLocation();
    //location.state 객체의 result 속성값을 변수로 지정해주는 부분
    const { result } = location.state || {};

    console.log(result);

    function handleFindPwPopup(event) {
        event.preventDefault();

        const width = 800;
        const height = 400;

        //화면 중앙 위치
        const left = (window.screenX / 2) + (window.outerWidth / 2) - (width / 2);
        const top = (window.screenY / 2) + (window.outerHeight / 2) - (height / 2);

        //경로는 소문자로
        window.open('/findPw', '_blank', `width=${width},height=${height},top=${top},left=${left},resizable=no,scrollbars=no`);

        window.close();
    }

    function handleCancel(e) {
        window.close();
    }

    return (
        <div>
            <div className='ResultIdContentWrap'>
                {/* 주의할 점! 객체를 직접 렌더링해서 쓰면 안되고 객체 속성별로 렌더링해줘야한다. */}
                <h1>아이디는 {result && (<div> {result.userId}</div>)} 입니다.</h1>
            </div>
            <div className='btnWrap'>
                {/*가입 버튼*/}
                <button type='button' className='findPwBtn' onClick={handleFindPwPopup}>비밀번호 찾기</button>
                {/*취소 버튼*/}
                <button type='button' className='cancelBtn' onClick={handleCancel}>취소</button>
            </div>
        </div>
    );
};

export default FindIdResult;