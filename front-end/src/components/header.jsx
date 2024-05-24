import React, { useState, useEffect } from "react";
import axios from "axios";
import "./header.css";
import logoMenu from "../assets/logo-menu.png";

export default function Header() {
    const [logoUrl, setLogoUrl] = useState(null);

    useEffect(() => {
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
    
    return (
        <header>
            <div className="header-container">
                {logoUrl && (
                    <a href="/">
                        <img className="headerLogo" src={logoUrl} alt="logo do Carim" />
                    </a>
                )}
                <div className="header-itens d-flex justify-content-around">
                    <div className="button-apoiar"><strong>Quero apoiar</strong> </div>
                    <a href="./Produtos">produtos</a>
                    <a href="./parametrizacao">Param</a>
                    <a href="./alterar">Alte</a>
                    <a href="./login">Login</a>
                    <a href="./register">Registrar</a>
                </div>
                <img className="logoMenu" src={logoMenu} alt="logo do Menu" />
            </div>
        </header>
    );
}
