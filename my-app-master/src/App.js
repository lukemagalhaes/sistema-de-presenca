import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Home from './pages/home';
import RegisterAbsence from './pages/registroFalta';
import ListAlunos from './pages/listAluno';
import BuscarAluno from './pages/buscarAluno';
import Relatorio from './pages/relatorio';
import './App.css'; // Se você tiver um arquivo CSS para estilização

function App() {
  const [fontSize, setFontSize] = useState(16);
  const [user] = useState({ name: "John Doe" }); // Simulação de um usuário logado
  const [theme, setTheme] = useState('light'); // Estado do tema (light ou dark)

  const toggleTheme = () => {
    setTheme(prevTheme => (prevTheme === 'light' ? 'dark' : 'light'));
  };

  const increaseFontSize = () => {
    setFontSize(prevFontSize => prevFontSize + 2);
  };

  const decreaseFontSize = () => {
    setFontSize(prevFontSize => (prevFontSize > 12 ? prevFontSize - 2 : 12));
  };

  return (
    <Router>
      <div className={`app ${theme}`} style={{ fontSize: `${fontSize}px` }}>
        <nav>
          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/registroFalta">Registrar Falta</Link></li>
            <li><Link to="/list-alunos">Listar Alunos</Link></li>
            <li><Link to="/relatorio-faltas">Relatorio de Faltas</Link></li>
          </ul>
        </nav>
        <div className="font-controls">
          <button className="font-button" onClick={decreaseFontSize}>A-</button>
          <button className="font-button" onClick={increaseFontSize}>A+</button>
        </div>



        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/registroFalta" element={<RegisterAbsence />} />
          <Route path="/list-alunos" element={<ListAlunos />} />
          <Route path="/buscar-aluno" element={<BuscarAluno />} />
          <Route path="/relatorio-faltas" element={<Relatorio />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
