import React, { useState, useEffect } from "react";
import Header from "./header";
import Footer from './footer';
import './parametrizacao.css';
import './Eventos.css';

export default function Eventos() {
    const [tiposDeEvento, setTiposDeEvento] = useState([]);
    const [selectedTypeId, setSelectedTypeId] = useState("");
    const [selectedTypeName, setSelectedTypeName] = useState("");
    const [dataEvento, setDataEvento] = useState("");

    useEffect(() => {
        fetchTiposDeEvento();
    }, []);

    const fetchTiposDeEvento = async () => {
        try {
            const token = localStorage.getItem("token");
            console.log("Token:", token); // Verifica se o token está sendo recuperado corretamente
            if (!token) {
                throw new Error("Token não encontrado");
            }

            const response = await fetch("http://localhost:8080/TipoEventos/get-all-Tipoevento", {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error(`Erro HTTP: ${response.status}`);
            }

            const data = await response.json();
            setTiposDeEvento(data);
        } catch (error) {
            console.error("Erro ao buscar tipos de eventos:", error);
        }
    };

    const getTipoNameById = (id) => {
        const tipo = tiposDeEvento.find(t => t.id === parseInt(id));
        return tipo ? tipo.nome : "";
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        // Verificar se a data é válida
        const selectedDate = new Date(dataEvento);
        const currentDate = new Date();
        if (selectedDate < currentDate) {
            alert("A data do evento não pode ser anterior à data atual.");
            return;
        }

        const eventoData = {
            nome: event.target.name.value,
            data: dataEvento,
            tipoId: selectedTypeId
        };

        try {
            const token = localStorage.getItem("token");
            if (!token) {
                throw new Error("Token não encontrado");
            }

            const response = await fetch("http://localhost:8080/Eventos/agendar-evento", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(eventoData)
            });

            if (!response.ok) {
                throw new Error(`Erro HTTP: ${response.status}`);
            }

            const responseData = await response.text();
            console.log(responseData);
            alert("Evento agendado com sucesso!");
        } catch (error) {
            console.error("Erro ao agendar o evento:", error);
            alert("Erro ao agendar o evento. Por favor, tente novamente.");
        }
    };

    return (
        <>
            <Header />
            <div className="header_evento">
                <h1>Agendamento de Eventos</h1>
            </div>

            <div className="corpo_evento">
                <div className="body_evento">
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="name">Nome do Evento:</label>
                        <input type="text" id="name" name="name" required />

                        <label htmlFor="data">Data do Evento:</label>
                        <input type="date" id="data" name="data" value={dataEvento} onChange={(e) => setDataEvento(e.target.value)} required />

                        <label htmlFor="tipoeventoSelect">Tipo de Evento:</label>
                        <select name="tipoeventoSelect" id="tipoeventoSelect" onChange={(e) => {
                            setSelectedTypeId(e.target.value);
                            setSelectedTypeName(getTipoNameById(e.target.value));
                        }} value={selectedTypeId}>
                            <option value="">Selecione um tipo...</option>
                            {tiposDeEvento.map((tipo) => (
                                <option key={tipo.id} value={tipo.id}>{tipo.nome}</option>
                            ))}
                        </select>
                        <button type="submit">Agendar</button>
                    </form>
                </div>
                <a href="./TipoEventos">Cadastrar Novo Tipo de Evento</a>
                <a href="./BuscaEventoNome">Buscar Eventos por Nome</a>
                <a href="./BuscaEventoTipo">Buscar Eventos por Tipo </a>
            </div>

            <Footer />
        </>
    );
}
