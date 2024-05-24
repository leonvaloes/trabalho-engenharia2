import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./register.css";
import Header from "./header";
import Footer from './footer';
import logo from '../assets/favicon-carim-site.png';

export default function Register() {
    const navigate = useNavigate();
    const [userNome, setUserNome] = useState("");
    const [userSenha, setUserSenha] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post("http://localhost:8080/auth/register", {
                login: userNome,
                password: userSenha,
                role: "USER" // Defina o papel conforme necessário
            });
            console.log("Usuário registrado com sucesso!", response.data);
            navigate("/login"); // Redirecionar para a página de login após o registro
        } catch (error) {
            console.error("Erro ao registrar usuário:", error);
            setError("Erro ao registrar usuário. Verifique os dados e tente novamente.");
        }
    };

    return (
        <>
            <Header />
            <main className="login-main">
                <form onSubmit={handleSubmit}>
                    <div className="login-container">
                        <h3>Cadastro Carim</h3>
                        <img src={logo} alt="Logo" />
                        <div className="login-div-input">
                            <input
                                type="text"
                                className="login-input"
                                required
                                placeholder="Nome de Usuário"
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
                        {error && <p className="error-message">{error}</p>}
                        <button type="submit">Cadastrar</button>
                    </div>
                </form>
            </main>
            <Footer />
        </>
    );
}
