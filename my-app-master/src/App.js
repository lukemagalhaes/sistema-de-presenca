// src/App.js
import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Home from './pages/home';
import RegisterAbsence from './pages/registroFalta';
import ListAlunos from './pages/listAluno';
import BuscarAluno from './pages/buscarAluno';
import './App.css'; // Se você tiver um arquivo CSS para estilização
import Relatorio from './pages/relatorio';

function App() {
  const [fontSize, setFontSize] = useState(16);

  const increaseFontSize = () => {
    setFontSize(prevFontSize => prevFontSize + 2);
  };

  const decreaseFontSize = () => {
    setFontSize(prevFontSize => (prevFontSize > 12 ? prevFontSize - 2 : 12));
  };

  return (
    <Router>
      <div style={{ fontSize: `${fontSize}px` }}>
        <nav>
          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/registroFalta">Registrar Falta</Link></li>
            <li><Link to="/list-alunos">Listar Alunos</Link></li>
            <li><Link to="/relatorio-faltas">Relatorio de Faltas</Link></li>
          </ul>
        </nav>
        <div className="font-controls">
          <button onClick={decreaseFontSize}>- Aa</button>
          <button onClick={increaseFontSize}>+ Aa</button>
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
