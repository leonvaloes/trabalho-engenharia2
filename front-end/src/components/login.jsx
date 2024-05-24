import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./login.css";
import Header from "./header";
import Footer from "./footer";
import logo from "../assets/favicon-carim-site.png";

export default function Login() {
  const navigate = useNavigate();
  const [userNome, setUserNome] = useState("");
  const [userSenha, setUserSenha] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/auth/login", {
        login: userNome,
        password: userSenha,
      });

      if (response.data.token) {
        console.log("Usuário autenticado com sucesso!", response.data);

        // Armazenar o token no localStorage
        localStorage.setItem("token", response.data.token);

        // Obter os papéis do usuário
        const rolesResponse = await axios.get("http://localhost:8080/auth/roles", {
          headers: {
            Authorization: `Bearer ${response.data.token}`,
          },
        });

        // Armazenar os papéis no localStorage
        localStorage.setItem("roles", JSON.stringify(rolesResponse.data));

        console.log("Token após o login:", localStorage.getItem("token"));
        console.log("Roles após o login:", localStorage.getItem("roles"));

        // Navegar para a rota '/produtos'
        navigate("/produtos");
      } else {
        setError("Usuário inativo. Entre em contato com o administrador.");
      }
    } catch (error) {
      console.error("Erro ao autenticar usuário:", error);
      setError("Erro ao autenticar usuário. Verifique os dados e tente novamente.");
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
            <button type="submit">Login</button>
          </div>
        </form>
      </main>
      <Footer />
    </>
  );
}
