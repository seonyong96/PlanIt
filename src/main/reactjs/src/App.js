import {useEffect, useState} from "react";
import { BrowserRouter,Routes, Route } from 'react-router-dom';  
import LoginPage from './pages/Login';
import FindIdPopup from './pages/FindIdPopup';
import FindPwPopup from './pages/FindPwPopup';
import RegisterPage from './pages/Register';
import Main from './pages/Main'

function App() {
    return (
        <BrowserRouter>
            <div>
                <Routes>
                    <Route path="/" element={<LoginPage/>}/>
                    <Route path="/findid" element={<FindIdPopup/>}/> 
                    <Route path="/findpw" element={<FindPwPopup/>}/> 
                    <Route path="/register" element={<RegisterPage/>}/> 
                    <Route path="/main" element={<Main/>}/> 
                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;