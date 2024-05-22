// src/pages/Report.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Report = () => {
  const [reports, setReports] = useState([]);

  useEffect(() => {
    axios.get('/api/faltas')
      .then(response => {
        setReports(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the reports!', error);
      });
  }, []);

  return (
    <div>
      <h1>Relatórios de Faltas</h1>
      <table>
        <thead>
          <tr>
            <th>Aluno</th>
            <th>Aula</th>
            <th>Presença</th>
            <th>Justificativa</th>
          </tr>
        </thead>
        <tbody>
          {reports.map(report => (
            <tr key={report.id_falta}>
              <td>{report.aluno.nome}</td>
              <td>{report.aula.conteudo}</td>
              <td>{report.presenca ? 'Presente' : 'Ausente'}</td>
              <td>{report.justificativa}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Report;
