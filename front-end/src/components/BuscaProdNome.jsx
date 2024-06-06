import React, { useState, useEffect } from 'react';
import Header from './header';
import Footer from './footer';
import './parametrizacao.css';
import './BuscaProdNome'
import './Produtos.css';
import './BuscaProdNome.css'

export default function BuscaProdutosNome() {
    const [nomeProduto, setNomeProduto] = useState('');
    const [produtos, setProdutos] = useState([]);
    const [tiposDeProduto, setTiposDeProduto] = useState([]);
    const [selectedProduto, setSelectedProduto] = useState(null);
    const [editMode, setEditMode] = useState(false);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchTiposDeProduto();
    }, []);

    // Função para buscar tipos de produto
    const fetchTiposDeProduto = async () => {
        try {
            const token = localStorage.getItem('token'); // Recupera token do localStorage
            const response = await fetch('http://localhost:8080/TipoProdutos/get-all-Tipoproduto', {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (!response.ok) {
                throw new Error('Falha ao buscar tipos de produtos');
            }
            const data = await response.json();
            setTiposDeProduto(data);
        } catch (error) {
            console.error('Erro ao buscar tipos de produtos:', error);
            setError('Erro ao buscar tipos de produtos. Tente novamente mais tarde.');
        }
    };

    // Função para lidar com a submissão do formulário de busca de produtos por nome
    const handleSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const nome = formData.get('name');

        try {
            const response = await fetch(`http://localhost:8080/Produtos/get-produto-nome?nome=${nome}`);
            if (response.ok) 
            {
                const data = await response.json();
                setProdutos(data);
            } 
            else 
            {
                setProdutos([]);
                console.error('Erro ao buscar produtos por nome:', response.statusText);
                setError('Erro ao buscar produtos por nome. Tente novamente mais tarde.');
            }
        } catch (error) {
            console.error('Erro ao buscar produtos por nome:', error);
            setError('Erro ao buscar produtos por nome. Tente novamente mais tarde.');
        }
    };

    // Função para editar um produto
    const handleEdit = (produto) => {
        setSelectedProduto(produto);
        setEditMode(true);
    };

    // Função para excluir um produto
    const handleDelete = async (prod) => {
        const confirmation = window.confirm("Tem certeza que deseja deletar este evento?");
        if (!confirmation) {
            return null;
        }
        try {
            const response = await fetch(`http://localhost:8080/Produtos/delete-Produto?id=${prod.id}`, { method: 'DELETE' });
            if (response.ok) {
                setProdutos(produtos.filter(produto => produto.id !== prod.id));
            } else {
                const error = await response.text();
                console.error('Erro ao excluir produto:', error);
                setError('Erro ao excluir produto. Tente novamente mais tarde.');
            }
        } catch (error) {
            console.error('Erro ao excluir produto:', error);
            setError('Erro ao excluir produto. Tente novamente mais tarde.');
        }
    };

    // Função para atualizar um produto
    const handleUpdate = async (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const updatedProduto = {
            id: selectedProduto.id,
            nome: formData.get('name'),
            estoque: formData.get('estoque'),
            tipoId: formData.get('tipoId')
        };

        try {
            const response = await fetch('http://localhost:8080/Produtos/update-produto', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                },
                body: JSON.stringify(updatedProduto)
            });
            if (response.ok) {
                const updatedProdutoWithTipo = {
                    ...updatedProduto,
                    tipo: tiposDeProduto.find(tipo => tipo.id === parseInt(updatedProduto.tipoId)).nome
                };
                setProdutos(produtos.map(prod => prod.id === selectedProduto.id ? updatedProdutoWithTipo : prod));
                setEditMode(false);
                setSelectedProduto(null);
            } else {
                const error = await response.text();
                console.error('Erro ao atualizar produto:', error);
                setError('Erro ao atualizar produto. Tente novamente mais tarde.');
            }
        } catch (error) {
            console.error('Erro ao atualizar produto:', error);
            setError('Erro ao atualizar produto. Tente novamente mais tarde.');
        }
    };

    return (
        <>
            <Header />
            <div className="header_prod">
                <h1>Busca de Produtos por Nome</h1>
            </div>

            <div className="container my-4">
                <div className="body_prod">
                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="name">Nome do Produto:</label>
                            <input type="text" className="form-control" id="name" name="name" required />
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
                                    <th scope="col">Nome do Produto</th>
                                    <th scope="col">Estoque</th>
                                    <th scope="col">Tipo de Produto</th>
                                    <th scope="col">Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                {produtos.map((produto, index) => (
                                    <tr key={produto.id}>
                                        <th scope="row">{index + 1}</th>
                                        <td>{produto.nome}</td>
                                        <td>{produto.estoque}</td>
                                        <td>{produto.tipo}</td>
                                        <td>
                                            <button className="btn btn-secondary mr-2" onClick={() => handleEdit(produto)}>Editar</button>
                                            <button className="btn btndanger" onClick={() => handleDelete(produto)}>Excluir</button>
</td>
</tr>
))}
{produtos.length === 0 && (
<tr>
<td colSpan="5" className="text-center">Nenhum produto encontrado.</td>
</tr>
)}
</tbody>
</table>
</div>
</div>

            {editMode && selectedProduto && (
                <div className="edit-form mt-4">
                    <h2>Editar Produto</h2>
                    <form onSubmit={handleUpdate}>
                        <div className="form-group">
                            <label htmlFor="editName">Nome do Produto:</label>
                            <input type="text" className="form-control" id="editName" name="name" defaultValue={selectedProduto.nome} required />
                        </div>
                        <div className="form-group">
                            <label htmlFor="editEstoque">Estoque:</label>
                            <input type="number" className="form-control" id="editEstoque" name="estoque" defaultValue={selectedProduto.estoque} required />
                        </div>
                        <div className="form-group">
                            <label htmlFor="editTipoId">Tipo de Produto:</label>
                            <select className="form-control" id="editTipoId" name="tipoId" defaultValue={selectedProduto.tipoId} required>
                                {tiposDeProduto.map(tipo => (
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