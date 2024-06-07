import React, { useState, useEffect } from "react";
import Header from "./header";
import Footer from './footer';
import './CriarRifa.css';

export default function CriarRifa() {
    const [formData, setFormData] = useState({
        nome: "",
        descricao: "",
        premio: "",
        valor: "",  
        dataSorteio: "",
        quantidadeTickets: ""
    });

    const [minDate, setMinDate] = useState("");

    useEffect(() => {
        const today = new Date();
        const yyyy = today.getFullYear();
        const mm = String(today.getMonth() + 1).padStart(2, '0'); 
        const dd = String(today.getDate()).padStart(2, '0');
        setMinDate(`${yyyy}-${mm}-${dd}`);
    }, []);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const qtd = formData.quantidadeTickets;
        if (qtd <= 0) {
            alert("Quantidade de tickets inválida");
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/Rifa', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                alert('Rifa cadastrada com sucesso!');
                setFormData({
                    nome: "",
                    descricao: "",
                    premio: "",
                    valor: "", 
                    dataSorteio: "",
                    quantidadeTickets: ""
                });
            } else {
                alert('Erro ao cadastrar a rifa.');
            }
        } catch (error) {
            console.error('Erro ao cadastrar a rifa:', error);
            alert('Erro ao cadastrar a rifa.');
        }
    };

    return (
        <>
            <Header />
            <div className="container">
                <h1>Cadastro de Rifa</h1>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="nome">Nome da Rifa</label>
                        <input type="text" id="nome" name="nome" value={formData.nome} onChange={handleChange} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="descricao">Descrição</label>
                        <textarea id="descricao" name="descricao" value={formData.descricao} onChange={handleChange} required></textarea>
                    </div>
                    <div className="form-group">
                        <label htmlFor="premio">Prêmio</label>
                        <input type="text" id="premio" name="premio" value={formData.premio} onChange={handleChange} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="valor">Valor</label>
                        <input type="number" id="valor" name="valor" value={formData.valor} onChange={handleChange} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="dataSorteio">Data do Sorteio</label>
                        <input type="date" id="dataSorteio" name="dataSorteio" value={formData.dataSorteio} onChange={handleChange} required min={minDate} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantidadeTickets">Quantidade de Tickets</label>
                        <input type="number" id="quantidadeTickets" name="quantidadeTickets" value={formData.quantidadeTickets} onChange={handleChange} required />
                    </div>
                    <button type="submit">Cadastrar Rifa</button>
                </form>
                <a href="./BuscaRifa">Mostrar rifas</a>
                <a href="./PesquisaRifa">Sortear rifa</a>
            </div>

            <Footer />
        </>
    );
}
