import React, { useState, useEffect } from 'react';
import Header from './header';
import Footer from './footer';
import './BuscaDoacao.css';

export default function BuscaDoacao() {
    const [doacoes, setDoacoes] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchDoacoes();
    }, []);

    // Função para buscar todas as doações
    const fetchDoacoes = async () => {
        try {
            const response = await fetch('http://localhost:8080/Doacao/all');
            if (response.ok) {
                const data = await response.json();
                setDoacoes(data);
            } else {
                console.error('Erro ao buscar doações:', response.statusText);
                setError('Erro ao buscar doações. Tente novamente mais tarde.');
            }
        } catch (error) {
            console.error('Erro ao buscar doações:', error);
            setError('Erro ao buscar doações. Tente novamente mais tarde.');
        }
    };

    // Função para excluir uma doação
    const handleDelete = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/Doacao/${id}`, {
                method: 'DELETE',
            });
            if (response.ok) {
                // Remove a doação da lista
                setDoacoes(doacoes.filter(doacao => doacao.id !== id));
            } else {
                const errorMessage = await response.text();
                console.error('Erro ao excluir doação:', errorMessage);
                setError('Erro ao excluir doação. Tente novamente mais tarde.');
            }
        } catch (error) {
            console.error('Erro ao excluir doação:', error);
            setError('Erro ao excluir doação. Tente novamente mais tarde.');
        }
    };

    return (
        <>
            <Header />
            <div className="header_doacao">
                <h1>Todas as Doações</h1>
            </div>

            <div className="container my-4">
                <div className="resultado mt-4">
                    <h2>Doações</h2>
                    {error && <div className="alert alert-danger" role="alert">{error}</div>}
                    <div className="table-responsive">
                        <table className="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Nome do Doador</th>
                                    <th scope="col">Telefone</th>
                                    <th scope="col">Item</th>
                                    <th scope='col'>Data</th>
                                    <th scope="col">Mensagem</th>
                                    
                                    <th scope="col">Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                {doacoes.map((doacao, index) => (
                                    <tr key={doacao.id}>
                                        <th scope="row">{index + 1}</th>
                                        <td>{doacao.nome}</td>
                                        <td>{doacao.telefone}</td>
                                        <td>{doacao.item}</td>
                                        <td>{doacao.data}</td>
                                        <td>{doacao.mensagem}</td>
                                        
                                        <td>
                                            <button className="btn btn-danger" onClick={() => handleDelete(doacao.id)}>Excluir</button>
                                        </td>
                                    </tr>
                                ))}
                                {doacoes.length === 0 && (
                                    <tr>
                                        <td colSpan="7" className="text-center">Nenhuma doação encontrada.</td>
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
