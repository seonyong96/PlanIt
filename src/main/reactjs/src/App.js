import {useEffect, useState} from "react";
import { BrowserRouter,Routes, Route } from 'react-router-dom';  
import LoginPage from './pages/Login';
import FindIdPopup from './pages/FindIdPopup';
import FindPwPopup from './pages/FindPwPopup';
import RegisterPage from './pages/Register';
import Main from './pages/Main'
import FindIdResult from "./pages/FindIdResult";
import ResetPw from "./pages/ResetPw";

function App() {
    return (
        <BrowserRouter>
            <div>
                <Routes>
                    <Route path="/" element={<LoginPage/>}/>
                    <Route path="/findid" element={<FindIdPopup/>}/> 
                    <Route path="/findidresult" element={<FindIdResult/>}/> 
                    <Route path="/findpw" element={<FindPwPopup/>}/> 
                    <Route path="/resetpw" element={<ResetPw/>}/> 
                    <Route path="/register" element={<RegisterPage/>}/> 
                    <Route path="/main" element={<Main/>}/> 
                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;