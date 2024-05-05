package br.com.sistemadepresenca.professor;

import jakarta.validation.constraints.NotEmpty;

public record ProfessorRequestDTO(
        @NotEmpty String nome,
        @NotEmpty String disciplina,
        @NotEmpty String formacao) {

}
