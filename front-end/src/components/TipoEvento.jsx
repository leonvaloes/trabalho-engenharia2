import React from "react";
import Header from "./header";
import Footer from './footer';
import './parametrizacao.css';
import './Eventos.css';

export default function TipoEventos() {
    const handleSubmit = async (event) => {
        event.preventDefault();

        const tipoEventoData = {
            nome: event.target.name.value,
        };

        try {
            const token = localStorage.getItem("token");
            if (!token) {
                throw new Error("Token não encontrado");
            }

            const response = await fetch("http://localhost:8080/TipoEventos/add-Tipoevento", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(tipoEventoData)
            });

            if (!response.ok) {
                throw new Error(`HTTP error status: ${response.status}`);
            }

            const responseData = await response.text();
            console.log(responseData);
            alert("Tipo Evento salvo com sucesso!");
        } catch (error) {
            console.error("Erro ao salvar o Tipo Evento:", error);
            alert("Erro ao salvar o Tipo Evento. Por favor, tente novamente.");
        }
    };

    return (
        <>
            <Header />
            <div className="header_evento">
                <h1>Cadastro de Tipos de Eventos</h1>
            </div>

            <div className="corpo_evento">
                <div className="body_evento">
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="name">Nome do Tipo Evento:</label>
                        <input type="text" id="name" name="name" required />
                        
                        <button type="submit">Salvar</button>
                    </form>
                </div>
                <a href="./Evento">Voltar para area de evento</a>
            </div>
            <Footer />
        </>
    );
}
