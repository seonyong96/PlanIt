// components/Header.js
import React from 'react';
import '../assets/header.css'; // 스타일을 따로 파일로 분리

const Header = () => {
  return (
    <header className="header">
      <div className="logo">
        <img src='/logo.png' className='logo-img'/>
      </div>
      <div className="header-right">
        <button className="notification-btn">🔔</button>
        <button className="profile-btn">👤</button>
      </div>
    </header>
  );
};

export default Header;
