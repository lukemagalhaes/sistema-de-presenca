import React, { useState, useEffect } from "react";
import axios from "axios";
import API_BASE_URL from "../ApiConfig";

const RegisterAbsence = () => {
  const [turmas, setTurmas] = useState([]);
  const [selectedTurma, setSelectedTurma] = useState("");
  const [students, setStudents] = useState([]);
  const [selectedStudent, setSelectedStudent] = useState("");
  const [attendance, setAttendance] = useState(true);
  const [justification, setJustification] = useState("");

  // Fetch all turmas on component mount
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

  // Fetch students when selectedTurma changes
  useEffect(() => {
    if (selectedTurma) {
      axios
        .get(`${API_BASE_URL}/api/alunos/turma/${selectedTurma}`)
        .then((response) => {
          setStudents(response.data);
        })
        .catch((error) => {
          console.error("There was an error fetching the students!", error);
        });
    } else {
      setStudents([]);
    }
  }, [selectedTurma]);

  const handleSubmit = (event) => {
    event.preventDefault();
    const absenceData = new URLSearchParams();
    absenceData.append('idAula', 1); // ID da aula, pode ser dinâmico
    absenceData.append('idAluno', selectedStudent);
    absenceData.append('presenca', attendance);
    absenceData.append('justificativa', justification);

    axios
      .post(`${API_BASE_URL}/api/faltas`, absenceData, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      })
      .then((response) => {
        alert("Falta registrada com sucesso!");
      })
      .catch((error) => {
        console.error("There was an error registering the absence!", error);
      });
  };

  return (
    <form onSubmit={handleSubmit}>
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
      <label>
        Aluno:
        <select
          value={selectedStudent}
          onChange={(e) => setSelectedStudent(e.target.value)}
          disabled={!selectedTurma}
        >
          <option value="">Selecione um aluno</option>
          {students.map((student) => (
            <option key={student.id_aluno} value={student.id_aluno}>
              {student.nome}
            </option>
          ))}
        </select>
      </label>
      <label>
        Presença:
        <input
          type="checkbox"
          checked={attendance}
          onChange={(e) => setAttendance(e.target.checked)}
        />
      </label>
      <label>
        Justificativa:
        <textarea
          value={justification}
          onChange={(e) => setJustification(e.target.value)}
        />
      </label>
      <button type="submit" disabled={!selectedStudent}>
        Registrar Falta
      </button>
    </form>
  );
};

export default RegisterAbsence;
