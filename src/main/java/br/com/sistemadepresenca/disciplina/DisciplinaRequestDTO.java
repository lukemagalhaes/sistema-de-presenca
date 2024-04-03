package br.com.sistemadepresenca.disciplina;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DisciplinaRequestDTO(
    
    @NotEmpty @NotNull int horario,
     
    @NotEmpty @NotNull int turma) {
    

}
