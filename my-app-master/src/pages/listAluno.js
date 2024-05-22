import React, { useState, useEffect } from "react";
import axios from "axios";
import API_BASE_URL from "../ApiConfig.js";

const ListAlunos = () => {
  const [alunos, setAlunos] = useState([]);
  const [turmas, setTurmas] = useState([]);
  const [selectedTurma, setSelectedTurma] = useState("");

  useEffect(() => {
    axios
      .get(`${API_BASE_URL}/api/turma`)
      .then((response) => {
        setTurmas(response.data);
      })
      .catch((error) => {
        console.error("There was an error fetching the turmas!", error);
      });
  }, []);

  useEffect(() => {
    if (selectedTurma) {
      axios
        .get(`${API_BASE_URL}/api/alunos/turma/${selectedTurma}`)
        .then((response) => {
          setAlunos(response.data);
        })
        .catch((error) => {
          console.error("There was an error fetching the students!", error);
        });
    } else {
      setAlunos([]);
    }
  }, [selectedTurma]);

  return (
    <div>
      <h1>Lista de Alunos</h1>
      <label>
        Turma:
        <select
          value={selectedTurma}
          onChange={(e) => setSelectedTurma(e.target.value)}
        >
          <option value="">Selecione uma turma</option>
          {turmas.map((turma) => (
            <option key={turma.id_turma} value={turma.id_turma}>
              {turma.nome}
            </option>
          ))}
        </select>
      </label>
      
      <div className="student-list">
        <div className="student-header">
            <span>Nome</span>
            <span>Ano de Ensino</span>
            <span>Série</span>
            <span>Período</span>
        </div>
        {alunos.map((aluno) => (
          <div key={aluno.id_aluno} className="student-row">
            <span>{aluno.nome}</span>
            <span>{aluno.turma.anoEnsino}</span>
            <span>{aluno.turma.serie}</span>
            <span>{aluno.turma.periodo}</span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ListAlunos;
