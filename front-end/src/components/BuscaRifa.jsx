import React, { useState, useEffect } from "react";
import Header from "./header";
import Footer from './footer';
import './BuscaRifa.css';

export default function BuscaRifa() {
    const [rifas, setRifas] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchRifas();
    }, []);

    // Função para buscar todas as rifas
    const fetchRifas = async () => {
        try {
            const response = await fetch('http://localhost:8080/Rifa/all');
            if (response.ok) {
                const data = await response.json();
                setRifas(data);
            } else {
                console.error('Erro ao buscar rifas:', response.statusText);
                setError('Erro ao buscar rifas. Tente novamente mais tarde.');
            }
        } catch (error) {
            console.error('Erro ao buscar rifas:', error);
            setError('Erro ao buscar rifas. Tente novamente mais tarde.');
        }
    };

    // Função para excluir uma rifa
    const handleDelete = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/Rifa/${id}`, {
                method: 'DELETE',
            });
            if (response.ok) {
                // Remove a rifa da lista
                setRifas(rifas.filter(rifa => rifa.id !== id));
            } else {
                const errorMessage = await response.text();
                console.error('Erro ao excluir rifa:', errorMessage);
                setError('Erro ao excluir rifa. Tente novamente mais tarde.');
            }
        } catch (error) {
            console.error('Erro ao excluir rifa:', error);
            setError('Erro ao excluir rifa. Tente novamente mais tarde.');
        }
    };

    // Função para sortear uma rifa
    const handleSortear = async (id, nome) => {
        try {
            const response = await fetch(`http://localhost:8080/Rifa/sortear/${id}`, {
                method: 'POST',
            });
            if (response.ok) {
                const winner = await response.json();
                alert(`Rifa "${nome}" sorteada! O vencedor é: ${winner.nome}`);
            } else {
                const errorMessage = await response.text();
                console.error('Erro ao sortear rifa:', errorMessage);
                setError('Erro ao sortear rifa. Tente novamente mais tarde.');
            }
        } catch (error) {
            console.error('Erro ao sortear rifa:', error);
            setError('Erro ao sortear rifa. Tente novamente mais tarde.');
        }
    };

    return (
        <>
            <Header />
            <div className="header_rifa">
                <h1>Todas as Rifas</h1>
            </div>

            <div className="container my-4">
                <div className="resultado mt-4">
                    <h2>Rifas</h2>
                    {error && <div className="alert alert-danger" role="alert">{error}</div>}
                    <div className="table-responsive">
                        <table className="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Nome da Rifa</th>
                                    <th scope="col">Prêmio</th>
                                    <th scope="col">Valor</th>
                                    <th scope="col">Data do Sorteio</th>
                                    <th scope="col">Quantidade de Tickets</th>
                                    <th scope="col">Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                {rifas.map((rifa, index) => (
                                    <tr key={rifa.id}>
                                        <th scope="row">{index + 1}</th>
                                        <td>
                                            <a href="#" onClick={() => handleSortear(rifa.id, rifa.nome)}>{rifa.nome}</a>
                                        </td>
                                        <td>{rifa.premio}</td>
                                        <td>{rifa.valor}</td>
                                        <td>{rifa.dataSorteio}</td>
                                        <td>{rifa.quantidadeTickets}</td>
                                        <td>
                                            <button className="btn btn-danger" onClick={() => handleDelete(rifa.id)}>Excluir</button>
                                        </td>
                                    </tr>
                                ))}
                                {rifas.length === 0 && (
                                    <tr>
                                        <td colSpan="8" className="text-center">Nenhuma rifa encontrada.</td>
                                    </tr>
                                )}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <Footer />
        </>
    );
}
