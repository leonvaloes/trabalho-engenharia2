import React from "react";
import './footer.css';
import logoCarim from '../assets/carim-logo-branco.png'
import { Link } from "react-router-dom";


export default function footer(){
    return(
        <>
            <div className="footer-area">
                <div className="footer-interno">
                    <Link to="/"><img src={logoCarim} alt="Logo da carim" /></Link>
                    <hr className="row"/>
                    <p className="footer-assinatura">© 2024 Carim</p>
                </div>
            </div>

        </>
    );
}