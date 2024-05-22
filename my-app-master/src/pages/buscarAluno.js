// src/pages/BuscarAluno.js
import React, { useState } from 'react';
import axios from 'axios';
import API_BASE_URL from '../ApiConfig';

const BuscarAluno = () => {
  const [nome, setNome] = useState('');
  const [alunos, setAlunos] = useState([]);

  const handleSearch = () => {
    axios.get(`${API_BASE_URL}/api/alunos/buscar`, { params: { aluno: nome } })
      .then(response => {
        setAlunos(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the students!', error);
      });
  };

  return (
    <div>
      <h1>Buscar Aluno</h1>
      <input
        type="text"
        value={nome}
        onChange={(e) => setNome(e.target.value)}
        placeholder="Nome do aluno"
      />
      <button onClick={handleSearch}>Buscar</button>
      <ul>
        {alunos.map(aluno => (
          <li key={aluno.id_aluno}>
            {aluno.nome}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default BuscarAluno;
