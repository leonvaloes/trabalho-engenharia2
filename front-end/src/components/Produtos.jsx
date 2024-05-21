import React, { useState, useEffect } from "react";
import './parametrizacao.css';
import Header from './header';
import Footer from './footer';
import './Produtos.css';

export default function Produtos() {

    const [tiposDeProduto, setTiposDeProduto] = useState([]);
    const [selectedTypeId, setSelectedTypeId] = useState("");
    const [selectedTypeName, setSelectedTypeName] = useState("");

    const fetchTiposDeProduto = async () => {
        try {
            const response = await fetch("http://localhost:8080/TipoProdutos/get-all-Tipoproduto");
            const data = await response.json();
            setTiposDeProduto(data);

            // Preenche o mapa de nomes de tipos de produtos por IDs
            const namesMap = data.reduce((acc, tipo) => {
                acc[tipo.id] = tipo.nome;
                return acc;
            }, {});
            setSelectedTypeId(Object.keys(namesMap)[0]); // Define o primeiro tipo como selecionado por padrão
            setSelectedTypeName(namesMap[Object.keys(namesMap)[0]]);
        } catch (error) {
            console.error("Erro ao buscar tipos de produtos:", error);
        }
    };

    useEffect(() => {
        fetchTiposDeProduto();
    }, []);

    const getTipoNameById = (id) => {
        const tipo = tiposDeProduto.find(t => t.id === parseInt(id));
        return tipo? tipo.nome : "";
    };

    const saveProduct = async () => {
        const productData = {
            nome: document.getElementById('name').value,
            estoque: document.getElementById('estoque').value,
            tipo: selectedTypeId 
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
        } catch (error) {
            console.error("Erro ao salvar o produto:", error);
        }
    };

    return (
        <>
            <Header />
            <div className="header_prod">
                <h1>Cadastro produtos</h1>
            </div>

            <div className="corpo_prod">
                <div className="body_prod">
                    <label htmlFor="name">Nome produto</label>
                    <input type="text" name="" id="name" />

                    <label htmlFor="estoque">Estoque</label>
                    <input type="number" name="" id="estoque" />
                    
                    <div>
                        <label htmlFor="type">Tipo produtos</label>
                        <select name="tipoproduto" id="tipoprodutoSelect" onChange={(e) => {
                            setSelectedTypeId(e.target.value);
                            setSelectedTypeName(getTipoNameById(e.target.value));
                        }}>
                             <option value="">Selecione um Tipo de Produto</option>
                            {tiposDeProduto.map((tipo) => (
                                <option key={tipo.id} value={tipo.id}>{tipo.nome}</option>
                            ))}
                        </select>
                    </div>

                    <button onClick={saveProduct}>Salvar</button>
                </div> 
            </div>
            
            <Footer />
        </>
    );
}
