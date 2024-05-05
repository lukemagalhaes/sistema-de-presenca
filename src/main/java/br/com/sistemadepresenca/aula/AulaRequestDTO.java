package br.com.sistemadepresenca.aula;

import java.sql.Date;

import br.com.sistemadepresenca.professor.Professor;
import br.com.sistemadepresenca.turma.Turma;
import jakarta.validation.constraints.NotEmpty;

public record AulaRequestDTO(

        @NotEmpty Long id_aula,
        @NotEmpty Turma turma,
        @NotEmpty Professor professor,
        @NotEmpty Date data,
        @NotEmpty String tipo,
        @NotEmpty String conteudo) {

}
