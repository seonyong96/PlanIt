import {useEffect, useState} from "react";
import { BrowserRouter,Routes, Route } from 'react-router-dom';  
import LoginPage from './pages/Login';

function App() {
    return (
        <BrowserRouter>
            <div>
                <Routes>
                    <Route path="/" element={<LoginPage/>}/> 
                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;
