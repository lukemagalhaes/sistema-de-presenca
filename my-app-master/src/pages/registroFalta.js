import React, { useState, useEffect } from "react";
import axios from "axios";
import API_BASE_URL from "../ApiConfig";

const RegisterAbsence = () => {
  const [turmas, setTurmas] = useState([]);
  const [selectedTurma, setSelectedTurma] = useState("");
  const [students, setStudents] = useState([]);
  const [selectedStudents, setSelectedStudents] = useState([]);
  const [aulas, setAulas] = useState([]);
  const [selectedAula, setSelectedAula] = useState("");
  const [professores, setProfessores] = useState([]);
  const [selectedProfessor, setSelectedProfessor] = useState("");

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
          setStudents(response.data);
        })
        .catch((error) => {
          console.error("There was an error fetching the students!", error);
        });
    } else {
      setStudents([]);
    }
  }, [selectedTurma]);
  
  useEffect(() => {
    axios
      .get(`${API_BASE_URL}/api/professor`)
      .then((response) => {
        setProfessores(response.data);
      })
      .catch((error) => {
        console.error("There was an error fetching the professors!", error);
      });
  }, []);

  useEffect(() => {
    if (selectedProfessor) {
      axios
        .get(`${API_BASE_URL}/api/aulas/por-professor/${selectedProfessor}`)
        .then((response) => {
          setAulas(response.data);
        })
        .catch((error) => {
          console.error("There was an error fetching the aulas!", error);
        });
    } else {
      setAulas([]);
    }
  }, [selectedProfessor]);

  const handleStudentSelection = (studentId) => {
    setSelectedStudents((prevSelected) =>
      prevSelected.includes(studentId)
        ? prevSelected.filter((id) => id !== studentId)
        : [...prevSelected, studentId]
    );
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    selectedStudents.forEach((studentId) => {
      const absenceData = new URLSearchParams();
      absenceData.append("idAula", selectedAula);
      absenceData.append("idAluno", studentId);
      absenceData.append("presenca", false);
      absenceData.append("justificativa", "n/a");

      axios
        .post(`${API_BASE_URL}/api/faltas`, absenceData, {
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
        })
        .then((response) => {
          console.log(`Falta registrada para o aluno ${studentId}`);
        })
        .catch((error) => {
          console.error(
            `There was an error registering the absence for student ${studentId}`,
            error
          );
        });
    });
    alert("Faltas registradas com sucesso!");
  };

  return (
    <div className="container">
      <form onSubmit={handleSubmit} className="form">
        <label>
          Turma:
          <select
            value={selectedTurma}
            onChange={(e) => setSelectedTurma(e.target.value)}
            className="select"
          >
            <option value="">Selecione uma turma</option>
            {turmas.map((turma) => (
              <option key={turma.id_turma} value={turma.id_turma}>
                {turma.serie}
              </option>
            ))}
          </select>
        </label>
        <label>
          Professor:
          <select
            value={selectedProfessor}
            onChange={(e) => setSelectedProfessor(e.target.value)}
            className="select"
          >
            <option value="">Selecione um professor</option>
            {professores.map((professor) => (
              <option key={professor.id_professor} value={professor.id_professor}>
                {professor.nome}
              </option>
            ))}
          </select>
        </label>
        <label>
          Aula:
          <select
            value={selectedAula}
            onChange={(e) => setSelectedAula(e.target.value)}
            disabled={!selectedTurma}
            className="select"
          >
            <option value="">Selecione uma aula</option>
            {aulas.map((aula) => (
              <option key={aula.id_aula} value={aula.id_aula}>
                {aula.tipo}: {aula.conteudo} - {aula.data}
              </option>
            ))}
          </select>
        </label>
        <button
          type="submit"
          disabled={
            !selectedAula ||
            selectedStudents.length === 0 ||
            !selectedProfessor
          }
          className="submit-button"
        >
          Registrar Falta
        </button>
      </form>
      <div className="student-list">
        <h3>Alunos</h3>
        {students.length > 0 ? (
          <ul className="student-ul">
            {students.map((student) => (
              <li key={student.id_aluno} className="student-li">
                <label className="student-label">
                  <input
                    type="checkbox"
                    checked={selectedStudents.includes(student.id_aluno)}
                    onChange={() =>
                      handleStudentSelection(student.id_aluno)
                    }
                    className="student-checkbox"
                  />
                  {student.nome}
               
                  </label>
              </li>
            ))}
          </ul>
        ) : (
          <p>Selecione uma turma e uma aula para listar os alunos.</p>
        )}
      </div>
    </div>
  );
};

export default RegisterAbsence;
