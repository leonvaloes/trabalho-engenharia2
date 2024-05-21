import React, { useState } from "react";
import "./header.css";
import headerLogo from "../assets/logo-carim.jpg"
import logoMenu from "../assets/logo-menu.png"
import { Link } from "react-router-dom";
import Routes from './routes'


export default function Header() {
    const [menuAberto, setEstadoMenu] = useState(false);

    function alterarEstadoMenu(){
        setEstadoMenu(!menuAberto);
        girarImagem();
    }

    function abrirMenu() {
        document.getElementsByClassName('.fundo-escuro').style.display = 'block';
    }
    function fecharMenu() {
        document.getElementsByClassName('.fundo-escuro').style.display = 'none';
    }
    
    function girarImagem() {
        const logoMenuElement = document.querySelector('.logoMenu');
        if (menuAberto) {
            logoMenuElement.style.transform = "rotate(0deg)";
        } else {
            logoMenuElement.style.transform = "rotate(90deg)";
        }
    }



    return (
        <header>
            <div className="header-container ">
                <a href="/"><img className="headerLogo" src={headerLogo} alt="logo do Carim" /></a>
                <div className="header-itens d-flex justify-content-around">
                    <div className="button-apoiar"><strong>Quero apoiar</strong> </div>
                    <a href="./parametrizacao">Param</a>
                    <a href="./alterar">Alte</a>
                    <a href="./login">Login</a>
                    <a href="./register">registrar</a>
                </div>
                <img className="logoMenu" onClick={alterarEstadoMenu} src={logoMenu} alt="logo do Menu" />
            </div>

            <div className="header-container-menu ">
                <nav className={menuAberto ? 'menu-aberto' : 'menu-fechado'}>
                    <ul className="header-lista">
                        <li><a href="/">Home</a></li>
                        <li><a href="./parametrizacao">Param</a></li>
                        <li><a href="./alterar">Alte</a></li>
                        <li><a href="./login">Login</a></li>
                        <li><a href="./register">Registrar</a></li>
                        <p>© 2024 <a href="#">Carim</a></p>
                    </ul>
                </nav>
            </div>
            <div className={menuAberto ? 'fundo-escuro-open' : 'fundo-escuro-close'}></div>
        </header>
    );
}