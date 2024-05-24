import React, { useState, useEffect } from "react";
import Header from "./header";
import Footer from './footer';
import './parametrizacao.css';
import './Produtos.css';
export default function TipoProdutos()
{
    const handleSubmit = async (event) => {
        event.preventDefault();

        const productData = {
            nome: event.target.name.value,  
        };

        try {
            const token = localStorage.getItem("token");
            if (!token) {
                throw new Error("Token não encontrado");
            }

            const response = await fetch("http://localhost:8080/TipoProdutos/add-Tipoprodutos", {
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
            alert("Tipo Produto salvo com sucesso!");
        } catch (error) {
            console.error("Erro ao salvar o Tipo produto:", error);
            alert("Erro ao salvar o Tipo produto. Por favor, tente novamente.");
        }
    };
    return(
        <>
        <Header/>
            <div className="header_prod">
                <h1>Cadastro de Produtos</h1>
            </div>

            <div className="corpo_prod"> 
                <div className="body_prod">
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="name">Nome do Tipo Produto:</label>
                        <input type="text" id="name" name="name" required />
                        
                        <button type="submit">Salvar</button>
                    </form>
                </div>
            </div>
        <Footer/>
        </>
    )
}