import {useEffect, useState} from "react";
import { BrowserRouter,Routes, Route } from 'react-router-dom';  
import LoginPage from './pages/Login';
import FindIdPassword from './pages/FindIdPassword';
import Main from './pages/Main'

function App() {
    return (
        <BrowserRouter>
            <div>
                <Routes>
                    <Route path="/" element={<LoginPage/>}/>
                    <Route path="/findidpassword" element={<FindIdPassword/>}/> 
                    <Route path="/main" element={<Main/>}/> 
                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;