package br.com.sistemadepresenca.aula;

import java.sql.Date;

import br.com.sistemadepresenca.professor.Professor;
import br.com.sistemadepresenca.turma.Turma;

public record AulaResponseDTO(Long id_aula, Turma turma, Professor professor, Date data, String tipo, String conteudo) {
    public AulaResponseDTO(Aula disciplina) {
        this(disciplina.getId_aula(), disciplina.getTurma(), disciplina.getProfessor(), disciplina.getData(),
                disciplina.getTipo(), disciplina.getConteudo());
    }
}
