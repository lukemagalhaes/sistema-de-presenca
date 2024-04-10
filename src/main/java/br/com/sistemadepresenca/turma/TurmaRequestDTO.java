package br.com.sistemadepresenca.turma;

import jakarta.validation.constraints.NotEmpty;

public record TurmaRequestDTO(
    @NotEmpty int anoEnsino,
    @NotEmpty String serie,
    @NotEmpty String periodo) {

}
