import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./header.css";
import logoMenu from "../assets/logo-menu.png";

export default function Header() {
    const [logoUrl, setLogoUrl] = useState(null);
    const navigate = useNavigate();
    const [isAdmin, setIsAdmin] = useState(false);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        // Verificar se o usuário é admin
        const roles = localStorage.getItem("roles");
        console.log("roles ", roles);
        if (roles && roles.includes("ADMIN")) {
            setIsAdmin(true);
        }

        // Verificar se o usuário está logado
        const token = localStorage.getItem("token");
        setIsLoggedIn(!!token);

        axios.get("http://localhost:8080/parametrizacao/logo-grande?parametrizacaoId=1", { responseType: "arraybuffer" })
            .then(response => {
                const blob = new Blob([response.data], { type: 'image/jpeg' });
                const imageUrl = URL.createObjectURL(blob);
                setLogoUrl(imageUrl);
            })
            .catch(error => {
                console.error("Erro ao obter a imagem do logo:", error);
            });
    }, []);

    // Função para fazer logout
    const handleLogout = () => {
        localStorage.removeItem("token"); // Remova o token do localStorage
        localStorage.removeItem("roles"); // Remova os papéis do localStorage
        navigate("/login"); // Redirecione para a página de login
    };

    return (
        <header>
            <div className="header-container">
                {logoUrl && (
                    <a href="/">
                        <img className="headerLogo" src={logoUrl} alt="logo do Carim" />
                    </a>
                )}
                <div className="header-itens d-flex justify-content-around">
                    {isLoggedIn && (
                        <div className="button-apoiar">
                            <strong>Quero apoiar</strong>
                        </div>
                    )}
                    {isLoggedIn && <a href="./Produtos">produtos</a>}
                    {isLoggedIn && <a href="./parametrizacao">adicionar parametrizaçãoo</a>}
                    {isLoggedIn && <a href="./alterar">Alterar parametrizaçãoo</a>}
                    {isLoggedIn && <a href="./Evento">Evento</a>}
                    {isAdmin && isLoggedIn && <a href="/admin">Admin</a>}
                    {isLoggedIn && <a href="#" onClick={handleLogout}>Logout</a>}
                    {!isLoggedIn && <a href="./login">Login</a>} {/* Renderizar o link de login apenas se não estiver logado */}
                    {!isLoggedIn && <a href="./register">Registrar</a>} {/* Renderizar o link de registro apenas se não estiver logado */}
                    
                </div>
                <img className="logoMenu" src={logoMenu} alt="logo do Menu" />
            </div>
        </header>
    );
}
