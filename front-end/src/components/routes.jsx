import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";

import Home from "./Home";
import Login from './login';
import Register from './register';
import Parametrizacao from './parametrizacao';
import Alterar from './alterar';

function routes(){
    return(
        <Router>
            <Routes>
                <Route element = { <Home/> }  path="/" exact />
                <Route element = { <Login/> }  path="/login" />
                <Route element = { <Register/> }  path="/register" />
                <Route element = { <Parametrizacao/> }  path="/parametrizacao" />
                <Route element = { <Alterar/> }  path="/alterar" />
            </Routes> 
        </Router>
    )
}

export default routes;