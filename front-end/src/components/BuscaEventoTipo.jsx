import React, { useState, useEffect } from 'react';
import Header from './header';
import Footer from './footer';
import './parametrizacao.css';
import './BuscaEventoTipo.css';

export default function BuscaEventosTipo() {
    const [eventos, setEventos] = useState([]);
    const [tiposDeEvento, setTiposDeEvento] = useState([]);
    const [selectedEvento, setSelectedEvento] = useState(null);
    const [editMode, setEditMode] = useState(false);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchTiposDeEvento();
    }, []);

    // Função para buscar tipos de evento
    const fetchTiposDeEvento = async () => {
        try {
            const token = localStorage.getItem('token'); // Recupera token do localStorage
            const response = await fetch('http://localhost:8080/TipoEventos/get-all-Tipoevento', {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (!response.ok) {
                throw new Error('Falha ao buscar tipos de eventos');
            }
            const data = await response.json();
            console.log('Tipos de Evento:', data); // Log de depuração
            setTiposDeEvento(data);
        } catch (error) {
            console.error('Erro ao buscar tipos de eventos:', error);
            setError('Erro ao buscar tipos de eventos. Tente novamente mais tarde.');
        }
    };

    // Função para lidar com a submissão do formulário de busca de eventos por tipo
    const handleSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const tipoId = formData.get('tipoId');

        try {
            const response = await fetch(`http://localhost:8080/Eventos/get-all-eventos-tipoevento?id=${tipoId}`);
            if (response.ok) {
                const data = await response.json();
                console.log('Eventos:', data); // Log de depuração
                const eventosComTipos = data.map(evento => {
                    const tipo = tiposDeEvento.find(t => t.id === parseInt(evento.tipoId));
                    console.log(`Evento: ${evento.nome}, Tipo: ${tipo ? tipo.nome : 'Tipo não encontrado'}`); // Log de depuração
                    return {
                        ...evento,
                        tipo: tipo ? tipo.nome : 'Tipo não encontrado'
                    };
                });
                setEventos(eventosComTipos);
            } else {
                setEventos([]);
                console.error('Erro ao buscar eventos por tipo:', response.statusText);
                setError('Erro ao buscar eventos por tipo. Tente novamente mais tarde.');
            }
        } catch (error) {
            console.error('Erro ao buscar eventos por tipo:', error);
            setError('Erro ao buscar eventos por tipo. Tente novamente mais tarde.');
        }
    };

    // Função para editar um evento
    const handleEdit = (evento) => {
        setSelectedEvento(evento);
        setEditMode(true);
    };

    // Função para excluir um evento
    const handleDelete = async (evento) => {
        const confirmation = window.confirm("Tem certeza que deseja deletar este evento?");
        if (!confirmation) {
            return null;
        }

        try {
            const response = await fetch(`http://localhost:8080/Eventos/delete-Evento?id=${evento.id}`, { method: 'DELETE' });
            if (response.ok) {
                setEventos(eventos.filter(event => event.id !== evento.id));
            } else {
                const error = await response.text();
                console.error('Erro ao excluir evento:', error);
                setError('Erro ao excluir evento. Tente novamente mais tarde.');
            }
        } catch (error) {
            console.error('Erro ao excluir evento:', error);
            setError('Erro ao excluir evento. Tente novamente mais tarde.');
        }
    };

    // Função para atualizar um evento
    const handleUpdate = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const updatedEvento = {
            id: selectedEvento.id,
            nome: formData.get('name'),
            data: formData.get('data'),
            tipoId: formData.get('tipoId')
        };

        try {
            const response = await fetch('http://localhost:8080/Eventos/update-evento', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                },
                body: JSON.stringify(updatedEvento)
            });
            if (response.ok) {
                const updatedEventoWithTipo = {
                    ...updatedEvento,
                    tipo: tiposDeEvento.find(t => t.id === parseInt(updatedEvento.tipoId))?.nome
                };
                setEventos(eventos.map(event => event.id === selectedEvento.id ? updatedEventoWithTipo : event));
                setEditMode(false);
                setSelectedEvento(null);
            } else {
                const error = await response.text();
                console.error('Erro ao atualizar evento:', error);
                setError('Erro ao atualizar evento. Tente novamente mais tarde.');
            }
        } catch (error) {
            console.error('Erro ao atualizar evento:', error);
            setError('Erro ao atualizar evento. Tente novamente mais tarde.');
        }
    };

    return (
        <>
            <Header />
            <div className="header_evento">
                <h1>Busca de Eventos por Tipo</h1>
            </div>

            <div className="container my-4">
                <div className="body_evento">
                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="tipoId">Tipo de Evento:</label>
                            <select className="form-control" id="tipoId" name="tipoId" required>
                                <option value="">Selecione o Tipo de Evento</option>
                                {tiposDeEvento.map(tipo => (
                                    <option key={tipo.id} value={tipo.id}>{tipo.nome}</option>
                                ))}
                            </select>
                        </div>
                        <button type="submit" className="btn btn-primary">Buscar</button>
                    </form>
                </div>

                <div className="resultado mt-4">
                    <h2>Resultados da Busca</h2>
                    {error && <div className="alert alert-danger" role="alert">{error}</div>}
                    <div className="table-responsive">
                        <table className="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Nome do Evento</th>
                                    <th scope="col">Data</th>
                                    <th scope="col">Tipo de Evento</th>
                                    <th scope="col">Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                {eventos.map((evento, index) => (
                                    <tr key={evento.id}>
                                        <th scope="row">{index + 1}</th>
                                        <td>{evento.nome}</td>
                                        <td>{evento.data}</td>
                                        <td>{evento.tipoEvento.nome}</td>
                                        <td>
                                            <button className="btn btn-secondary mr-2" onClick={() => handleEdit(evento)}>Editar</button>
                                            <button className="btn btn-danger" onClick={() => handleDelete(evento)}>Excluir</button>
                                        </td>
                                    </tr>
                                ))}
                                {eventos.length === 0 && (
                                    <tr>
                                        <td colSpan="5" className="text-center">Nenhum evento encontrado.</td>
                                    </tr>
                                )}
                            </tbody>
                        </table>
                    </div>
                </div>

                {editMode && selectedEvento && (
                    <div className="edit-form mt-4">
                        <h2>Editar Evento</h2>
                        <form onSubmit={handleUpdate}>
                            <div className="form-group">
                                <label htmlFor="editName">Nome do Evento:</label>
                                <input type="text" className="form-control" id="editName" name="name" defaultValue={selectedEvento.nome} required />
                            </div>
                            <div className="form-group">
                                <label htmlFor="editData">Data:</label>
                                <input type="date" className="form-control" id="editData" name="data" defaultValue={selectedEvento.data} required />
                            </div>
                            <div className="form-group">
                                <label htmlFor="editTipoId">Tipo de Evento:</label>
                                <select className="form-control" id="editTipoId" name="tipoId" defaultValue={selectedEvento.tipoId} required>
                                    {tiposDeEvento.map(tipo => (
                                        <option key={tipo.id} value={tipo.id}>{tipo.nome}</option>
                                    ))}
                                </select>
                            </div>
                            <button type="submit" className="btn btn-primary">Atualizar</button>
                            <button type="button" className="btn btn-secondary ml-2" onClick={() => setEditMode(false)}>Cancelar</button>
                        </form>
                    </div>
                )}
            </div>

            <Footer />
        </>
    );
}
