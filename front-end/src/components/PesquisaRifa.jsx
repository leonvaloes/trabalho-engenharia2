import React, { useState, useEffect } from "react";
import Header from "./header";
import Footer from "./footer";
import "./PesquisaRifa.css";

export default function PesquisarRifa() {
  const [rifas, setRifas] = useState([]);
  const [error, setError] = useState("");
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    if (searchTerm.trim() !== "") {
      searchRifas();
    }
  }, [searchTerm]);

  const searchRifas = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/Rifa/search?nome=${searchTerm}`
      );
      if (response.ok) {
        const data = await response.json();
        setRifas(data);
      } else {
        console.error("Erro ao buscar rifas:", response.statusText);
        setError("Erro ao buscar rifas. Tente novamente mais tarde.");
      }
    } catch (error) {
      console.error("Erro ao buscar rifas:", error);
      setError("Erro ao buscar rifas. Tente novamente mais tarde.");
    }
  };

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const clearSearch = () => {
    setRifas([]);
    setSearchTerm("");
  };

  return (
    <>
      <Header />
      <div className="container">
        <h1>Pesquisar Rifa</h1>
        <div className="search-form">
          <input
            type="text"
            placeholder="Pesquisar por nome da rifa..."
            value={searchTerm}
            onChange={handleSearchChange}
          />
          <button onClick={searchRifas}>Pesquisar</button>
        </div>
        {rifas.length > 0 ? (
          <div className="rifas-list">
            <h2>Resultados da Pesquisa</h2>
            <ul>
              {rifas.map((rifa) => (
                <li key={rifa.id}>
                  <h3>{rifa.nome}</h3>
                  <p>Prêmio: {rifa.premio}</p>
                  <p>Valor: {rifa.valor}</p>
                  <p>Data do Sorteio: {rifa.dataSorteio}</p>
                  <p>Quantidade de Tickets: {rifa.quantidadeTickets}</p>
                </li>
              ))}
            </ul>
          </div>
        ) : (
          <p>Nenhum resultado encontrado.</p>
        )}
        {error && <p className="error">{error}</p>}
      </div>
      <Footer />
    </>
  );
}
