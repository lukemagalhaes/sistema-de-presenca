import React, { useState, useEffect } from "react";
import axios from "axios";
import API_BASE_URL from "../ApiConfig.js";
import "./ListAlunos.css";

const ListAlunos = () => {
  const [alunos, setAlunos] = useState([]);
  const [turmas, setTurmas] = useState([]);
  const [selectedTurma, setSelectedTurma] = useState("");
  const [faltas, setFaltas] = useState({});

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
      setFaltas({});
    }
  }, [selectedTurma]);

  useEffect(() => {
    const fetchFaltas = async () => {
      if (alunos.length > 0) {
        const novasFaltas = {};
        for (const aluno of alunos) {
          try {
            const response = await axios.get(`${API_BASE_URL}/api/faltas/aluno/${aluno.id_aluno}`);
            const totalAulas = response.data.length;
            const totalFaltas = response.data.filter(falta => !falta.presenca).length;
            novasFaltas[aluno.id_aluno] = {
              totalAulas,
              totalFaltas,
              porcentagemFaltas: (totalFaltas / totalAulas) * 100,
            };
          } catch (error) {
            console.error(`Erro ao buscar faltas para o aluno ${aluno.id_aluno}:`, error);
            novasFaltas[aluno.id_aluno] = {
              totalAulas: 0,
              totalFaltas: 0,
              porcentagemFaltas: 0,
            };
          }
        }
        setFaltas(novasFaltas);
      }
    };

    fetchFaltas();
  }, [alunos]);

  const handleSendEmail = (email) => {
    window.location.href = `mailto:${email}`;
  };

  return (
    <div className="container">
      <h1>Lista de Alunos</h1>
      <label>
        Turma:
        <select
          className="select"
          value={selectedTurma}
          onChange={(e) => setSelectedTurma(e.target.value)}
        >
          <option value="">Selecione uma turma</option>
          {turmas.map((turma) => (
            <option key={turma.id_turma} value={turma.id_turma}>
              {turma.serie}
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
          <span>Faltas (%)</span>
          <span>Email</span>
        </div>
        {alunos.map((aluno) => (
          <div key={aluno.id_aluno} className="student-row">
            <span>{aluno.nome}</span>
            <span>{aluno.turma.anoEnsino}</span>
            <span>{aluno.turma.serie}</span>
            <span>{aluno.turma.periodo}</span>
            <span>{(faltas[aluno.id_aluno]?.porcentagemFaltas || 0).toFixed(2)}%</span>
            <button
              className="email-button"
              onClick={() => handleSendEmail(aluno.email)}
              disabled={(faltas[aluno.id_aluno]?.porcentagemFaltas || 0) <= 20}
            >
              Enviar Email
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ListAlunos;
