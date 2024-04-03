package br.com.sistemadepresenca.turma;

import jakarta.validation.constraints.NotEmpty;

public record TurmaRequestDTO(
    @NotEmpty String anoEnsino) {
    
}
