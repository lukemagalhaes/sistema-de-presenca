package br.com.sistemadepresenca.aluno;

import java.sql.Date;

import br.com.sistemadepresenca.turma.Turma;
import jakarta.validation.constraints.NotEmpty;

public record AlunoRequestDTO(
    @NotEmpty Long id_aluno, 
    @NotEmpty Turma turma, 
    @NotEmpty String nome,
    @NotEmpty int idade,
    @NotEmpty String genero,
    @NotEmpty Date dataNascimento, 
    @NotEmpty String endereco) {
}
