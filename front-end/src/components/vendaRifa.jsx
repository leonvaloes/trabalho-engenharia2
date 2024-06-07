// components/ComprarRifa.js
import React, { useState } from 'react';
import axios from 'axios';
import { useParams, useHistory } from 'react-router-dom';

const ComprarRifa = () => {
  const { id } = useParams();
  const history = useHistory();
  const [quantidade, setQuantidade] = useState(1);

  const handleCompra = () => {
    axios.post('/api/vendas', {
      user: { id: 'user-id' },  // substituir pelo ID do usuário autenticado
      rifa: { id: id },
      quantidade: quantidade
    })
    .then(response => {
      console.log('Compra realizada com sucesso', response.data);
      history.push('/');
    })
    .catch(error => console.error('Erro na compra:', error));
  };

  return (
    <div>
      <h1>Comprar Rifa</h1>
      <input 
        type="number" 
        value={quantidade} 
        onChange={e => setQuantidade(e.target.value)} 
        min="1" 
      />
      <button onClick={handleCompra}>Comprar</button>
    </div>
  );
};

export default vendaRifa;
