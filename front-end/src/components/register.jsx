import React, { useState } from "react";
import axios from "axios";
import "./login.css";
import Header from "./header";
import Footer from './footer';
import logo from '../assets/favicon-carim-site.png';

export default function Login() {
    const [nome, setNome] = useState("");
    const [senha, setSenha] = useState("");

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/login/criar-conta', {
                userNome: nome,
                userSenha: senha
            });
            console.log('Account creation successful', response.data);
            // Handle successful account creation (e.g., redirect to login page or dashboard)
        } catch (error) {
            console.error('Error creating account', error);
            // Handle account creation error (e.g., show error message)
        }
    };

    return (
        <>
            <Header />
            <main className="login-main">
                <form onSubmit={handleSubmit}>
                    <div className="login-container">
                        <h3>Login Carim</h3>
                        <img src={logo} alt="Logo" />
                        <div className="login-div-input">
                            <input
                                type="text"
                                className="login-input"
                                required
                                placeholder="Nome de Usuario"
                                name="nome"
                                id="nome"
                                value={nome}
                                onChange={(e) => setNome(e.target.value)}
                            />
                            <input
                                type="password"
                                className="login-input"
                                required
                                placeholder="Senha"
                                name="senha"
                                id="senha"
                                value={senha}
                                onChange={(e) => setSenha(e.target.value)}
                            />
                        </div>
                        <button type="submit">Registrar</button>
                    </div>
                </form>
            </main>
            <Footer />
        </>
    );
}
