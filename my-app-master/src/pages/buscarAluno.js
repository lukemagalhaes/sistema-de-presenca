// src/pages/RelatorioFaltas.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import API_BASE_URL from '../ApiConfig';
import './RelatorioFaltas.css';

const RelatorioFaltas = () => {
  const [relatorio, setRelatorio] = useState([]);

  useEffect(() => {
    axios.get(`${API_BASE_URL}/api/faltas`)
      .then(response => {
        const groupedData = groupFaltas(response.data);
        setRelatorio(groupedData);
      })
      .catch(error => {
        console.error('There was an error fetching the absence report!', error);
      });
  }, []);

  const groupFaltas = (data) => {
    const grouped = {};
    data.forEach(item => {
      const { aluno, aula, justificativa } = item;
      const key = `${aluno.turma.anoEnsino}-${aluno.turma.serie}-${aula.professor.nome}-${aula.professor.disciplina}`;
      if (!grouped[key]) {
        grouped[key] = [];
      }
      grouped[key].push({ aluno: aluno.nome, dataFalta: aula.data, justificativa });
    });
    return grouped;
  };

  return (
    <div className="relatorio-container"> {}
      <h1>Relatório de Faltas</h1>
      {Object.entries(relatorio).map(([key, faltas]) => (
        <div key={key} className="relatorio-section"> {}
          <h2>{key}</h2>
          <div className="table-responsive"> {}
            <table>
              <thead>
                <tr>
                  <th>Data da Falta</th>
                  <th>Justificativa</th>
                </tr>
              </thead>
              <tbody>
                {faltas.map((falta, index) => (
                  <tr key={index}>
                    <td>{falta.dataFalta}</td>
                    <td>{falta.justificativa}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      ))}
    </div>
  );
};

export default RelatorioFaltas;
