import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./register.css";
import Header from "./header";
import Footer from './footer';
import logo from '../assets/favicon-carim-site.png';

export default function Login() {
    const [userNome, setUserNome] = useState("");
    const [userSenha, setUserSenha] = useState("");
    const navigate = useNavigate();


    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/login/criar-conta", {
                userNome: userNome,
                userSenha: userSenha
            });
            console.log(response.data);
            alert("Conta criada com sucesso!");
            navigate("/parametrizacao"); // Redireciona para a tela "parametrizacao"
        } catch (error) {
            console.error("Houve um erro ao criar a conta!", error);
            alert("username já cadastrado");
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
                                value={userNome}
                                onChange={(e) => setUserNome(e.target.value)}
                                name="userNome"
                                id="userNome"
                            />
                            <input
                                type="password"
                                className="login-input"
                                required
                                placeholder="Senha"
                                value={userSenha}
                                onChange={(e) => setUserSenha(e.target.value)}
                                name="userSenha"
                                id="userSenha"
                            />
                        </div>
                        <button type="submit">Cadastrar</button>
                    </div>
                </form>
            </main>
            <Footer />
        </>
    );
}
