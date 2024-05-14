import React from "react";
import "./login.css";
import Header from "./header"
import Footer from './footer'
import logo from '../assets/favicon-carim-site.png'

export default function login() {
    return (
        <>
        <Header/>
            <main className="login-main">
                <form method="post">
                    <div className="login-container">
                        <h3>Login Carim</h3>
                        <img src={logo} alt="" />
                        <div className="login-div-input">
                            <input type="text" className="login-input" required placeholder="Nome de Usuario" name="nome" id="nome" />
                            <input type="password" className="login-input" required placeholder="Senha" name="senha" id="senha" />
                        </div>
                        <div className="login-align">
                            <div className="login-div-check">
                                <input type="checkbox" name="login-check" id="login-check" />
                                <label htmlFor="login-check">Remember me</label>
                            </div>
                            <a href="#">Esqueci a senha</a>
                            
                        </div>
                        <button>ENTRAR</button>

                    </div>
                </form>
            </main>
            <Footer/>
        </>
        

    )
}