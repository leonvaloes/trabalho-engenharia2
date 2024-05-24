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
            const token = localStorage.getItem("token");
            if (!token) {
                throw new Error("Token não encontrado");
            }
            console.log("Token:", token); // Printa o token antes de fazer a requisição

            const response = await fetch("http://localhost:8080/TipoProdutos/get-all-Tipoproduto", {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error(`HTTP error status: ${response.status}`);
            }

            const data = await response.json();
            setTiposDeProduto(data);
        } catch (error) {
            console.error("Erro ao buscar tipos de produtos:", error);
        }
    };

    const getTipoNameById = (id) => {
        const tipo = tiposDeProduto.find(t => t.id === parseInt(id));
        return tipo ? tipo.nome : "";
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const productData = {
            nome: event.target.name.value,
            estoque: event.target.estoque.value,
            tipoId: selectedTypeId
        };

        try {
            const token = localStorage.getItem("token");
            if (!token) {
                throw new Error("Token não encontrado");
            }

            const response = await fetch("http://localhost:8080/Produtos/add-produtos", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
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

                        <label htmlFor="estoque"> Estoque:</label>
                        <input type="number" id="estoque" name="estoque" required />

                        <label htmlFor="tipoprodutoSelect"> Tipo de Produto:</label>
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
                
                <a href="./BuscaProdNome">Buscar Produtos por Nome</a>
                <a href="./BuscaProdEstoque">Buscar produtos por Estoque</a>
                
            </div>

            <Footer />
        </>
    );
}
