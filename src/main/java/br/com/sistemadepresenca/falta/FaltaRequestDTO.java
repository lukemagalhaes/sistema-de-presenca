package br.com.sistemadepresenca.falta;

import br.com.sistemadepresenca.aluno.Aluno;
import br.com.sistemadepresenca.aula.Aula;
import jakarta.validation.constraints.NotEmpty;

public record FaltaRequestDTO(
    @NotEmpty Long id_presenca, 
    @NotEmpty Aluno aluno,
    @NotEmpty Aula aula,
    @NotEmpty boolean presenca,
    String justificativa) {
    
}
