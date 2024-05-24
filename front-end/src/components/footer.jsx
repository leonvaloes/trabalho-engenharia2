import React, { useState, useEffect } from "react";
import axios from "axios"; // Importe o axios aqui
import './footer.css';
import { Link } from "react-router-dom";

export default function Footer() {
    const [logoUrl, setLogoUrl] = useState(null);
    useEffect(() => {
        axios.get("http://localhost:8080/parametrizacao/logo-pequeno?parametrizacaoId=1", { responseType: "arraybuffer" })
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
        <>
            <div className="footer-area">
                <div className="footer-interno">
                    {logoUrl && (
                        <a href="/">
                            <img className="headerLogo" src={logoUrl} alt="logo do Carim" />
                        </a>
                    )}
                    <hr className="row" />
                    <p className="footer-assinatura">© 2024 Carim</p>
                </div>
            </div>
        </>
    );
}
