import React, { useState, useEffect } from "react";
import Header from "./header";
import Footer from './footer';
import './parametrizacao.css';
import './Produtos.css';

export default function Produtos() {
    const [tiposDeProduto, setTiposDeProduto] = useState([]);
    const [selectedTypeId, setSelectedTypeId] = useState("");
    const [selectedTypeName, setSelectedTypeName] = useState("");

    useEffect(() => {
        fetchTiposDeProduto();
    }, []);

    const fetchTiposDeProduto = async () => {
        try {
            const response = await fetch("http://localhost:8080/TipoProdutos/get-all-Tipoproduto");
            const data = await response.json();
            setTiposDeProduto(data);

            const namesMap = data.reduce((acc, tipo) => {
                acc[tipo.id] = tipo.nome;
                return acc;
            }, {});

            setSelectedTypeId(Object.keys(namesMap)[0]);
            setSelectedTypeName(namesMap[Object.keys(namesMap)[0]]);
        } catch (error) {
            console.error("Erro ao buscar tipos de produtos:", error);
        }
    };

    const getTipoNameById = (id) => {
        const tipo = tiposDeProduto.find(t => t.id === parseInt(id));
        return tipo ? tipo.nome : "";
    };

    const handleSubmit = async (event) => {
        event.preventDefault(); // Impede o comportamento padrão do formulário

        const productData = {
            nome: event.target.name.value,
            estoque: event.target.estoque.value,
            tipoId: selectedTypeId // Ajuste para enviar diretamente o ID do tipo de produto
        };

        try {
            const response = await fetch("http://localhost:8080/Produtos/add-produtos", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(productData)
            });

            if (!response.ok) {
                throw new Error(`HTTP error status: ${response.status}`);
            }

            const responseData = await response.text();
            console.log(responseData);
            alert("Produto salvo com sucesso!");
        } catch (error) {
            console.error("Erro ao salvar o produto:", error);
            alert("Erro ao salvar o produto. Por favor, tente novamente.");
        }
    };

    return (
        <>
            <Header />
            <div className="header_prod">
                <h1>Cadastro de Produtos</h1>
            </div>

            <div className="corpo_prod">
                <div className="body_prod">
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="name">Nome do Produto:</label>
                        <input type="text" id="name" name="name" required />

                        <label htmlFor="estoque">Estoque:</label>
                        <input type="number" id="estoque" name="estoque" required />

                        <label htmlFor="tipoprodutoSelect">Tipo de Produto:</label>
                        <select name="tipoprodutoSelect" id="tipoprodutoSelect" onChange={(e) => {
                            setSelectedTypeId(e.target.value);
                            setSelectedTypeName(getTipoNameById(e.target.value));
                        }} value={selectedTypeId}>
                            <option value="">Selecione um tipo...</option>
                            {tiposDeProduto.map((tipo) => (
                                <option key={tipo.id} value={tipo.id}>{tipo.nome}</option>
                            ))}
                        </select>
                        <button type="submit">Salvar</button>
                    </form>
                </div>
            </div>

            <Footer />
        </>
    );
}
