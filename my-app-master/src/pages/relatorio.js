import React, { useState, useEffect } from 'react';
import axios from 'axios';
import API_BASE_URL from "../ApiConfig.js";

const Relatorio = () => {
  const [reports, setReports] = useState([]);
  const [groupBy, setGroupBy] = useState('date');
  const [groupedReports, setGroupedReports] = useState({});
  const [students, setStudents] = useState([]);

  useEffect(() => {
    // Fetch all students first
    axios.get(`${API_BASE_URL}/api/alunos`)
      .then(response => {
        setStudents(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the students!', error);
      });
  }, []);

  useEffect(() => {
    if (students.length > 0) {
      const fetchStudentAbsences = async () => {
        const absences = [];
        for (const student of students) {
          try {
            const response = await axios.get(`${API_BASE_URL}/api/faltas/aluno/${student.id_aluno}`);
            const studentAbsences = response.data.filter(falta => !falta.presenca);
            absences.push(...studentAbsences);
          } catch (error) {
            console.error(`Erro ao buscar faltas para o aluno ${student.id_aluno}:`, error);
          }
        }
        setReports(absences);
      };

      fetchStudentAbsences();
    }
  }, [students]);

  useEffect(() => {
    const grouped = groupReports(reports, groupBy);
    setGroupedReports(grouped);
  }, [reports, groupBy]);

  const groupReports = (reports, criterion) => {
    return reports.reduce((acc, report) => {
      let key;
      switch (criterion) {
        case 'date':
          key = new Date(report.aula.data).toLocaleDateString();
          break;
        case 'year':
          key = report.aluno.turma.anoEnsino;
          break;
        case 'class':
          key = report.aluno.turma.serie;
          break;
        case 'professor':
          key = report.aula.professor.nome;
          break;
        case 'subject':
          key = report.aula.tipo;
          break;
        case 'student':
          key = report.aluno.nome;
          break;
        default:
          key = new Date(report.aula.data).toLocaleDateString();
      }

      if (!acc[key]) {
        acc[key] = [];
      }

      acc[key].push(report);
      return acc;
    }, {});
  };

  return (
    <div>
      <h1>Relatórios de Faltas</h1>
      <label>
        Agrupar por:
        <select value={groupBy} onChange={(e) => setGroupBy(e.target.value)}>
          <option value="date">Data</option>
          <option value="year">Ano de Ensino</option>
          <option value="class">Turma</option>
          <option value="professor">Professor</option>
          <option value="subject">Disciplina</option>
          <option value="student">Aluno</option>
        </select>
      </label>
      {Object.entries(groupedReports).map(([group, reports]) => (
        <div key={group}>
          <h2>{group}</h2>
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
      ))}
    </div>
  );
};

export default Relatorio;
