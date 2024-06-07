import React, { useState, useEffect } from "react";
import Header from "./header";
import Footer from './footer';
import './AgendarDoacao.css';

export default function AgendarDoacao() {
    const [formData, setFormData] = useState({
        item: "",
        quantidade: "",
        nome: "",
        email: "",
        telefone: "",
        mensagem: "",
        data: "" // Novo campo de data
    });

    const [minDate, setMinDate] = useState("");

    useEffect(() => {
        // Define a data mínima como a data atual
        const today = new Date();
        const yyyy = today.getFullYear();
        const mm = String(today.getMonth() + 1).padStart(2, '0'); // Janeiro é 0
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
        const qnt = formData.quantidade;
        if (qnt < 0) {
            alert("Quantidade inválida");
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/Doacao', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                alert('Doação enviada com sucesso!');
                setFormData({
                    item: "",
                    quantidade: "",
                    nome: "",
                    email: "",
                    telefone: "",
                    mensagem: "",
                    data: "" // Resetar o campo de data
                });
            } else {
                alert('Erro ao enviar a doação.');
            }
        } catch (error) {
            console.error('Erro ao enviar a doação:', error);
            alert('Erro ao enviar a doação.');
        }
    };

    return (
        <>
            <Header />
            <div className="container">
                <h1>Formulário de Doação</h1>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="item">Item de Doação</label>
                        <input type="text" id="item" name="item" value={formData.item} onChange={handleChange} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="quantidade">Quantidade</label>
                        <input type="number" id="quantidade" name="quantidade" value={formData.quantidade} onChange={handleChange} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="nome">Nome do Doador</label>
                        <input type="text" id="nome" name="nome" value={formData.nome} onChange={handleChange} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <input type="email" id="email" name="email" value={formData.email} onChange={handleChange} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="telefone">Telefone</label>
                        <input type="tel" id="telefone" name="telefone" value={formData.telefone} onChange={handleChange} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="mensagem">Mensagem</label>
                        <textarea id="mensagem" name="mensagem" value={formData.mensagem} onChange={handleChange}></textarea>
                    </div>
                    <div className="form-group">
                        <label htmlFor="data">Data</label>
                        <input type="date" id="data" name="data" value={formData.data} onChange={handleChange} required min={minDate} />
                    </div>
                    <button type="submit">Enviar Doação</button>
                </form>

                <a href="./BuscaDoacao">Mostrar doações</a>
            </div>

            <Footer />
        </>
    );
}
