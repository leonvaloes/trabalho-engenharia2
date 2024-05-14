import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";

import Home from "./Home";
import Login from './login';


function routes(){
    return(
        <Router>
            <Routes>
                <Route element = { <Home/> }  path="/" exact />
                <Route element = { <Login/> }  path="/login" />
            </Routes> 
        </Router>
    )
}

export default routes;