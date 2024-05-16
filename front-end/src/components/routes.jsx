import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";

import Home from "./Home";
import Login from './login';
import Parametrizacao from './parametrizacao';


function routes(){
    return(
        <Router>
            <Routes>
                <Route element = { <Home/> }  path="/" exact />
                <Route element = { <Login/> }  path="/login" />
                <Route element = { <Parametrizacao/> }  path="/parametrizacao" />
            </Routes> 
        </Router>
    )
}

export default routes;