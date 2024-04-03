package br.com.sistemadepresenca.aluno;

import jakarta.validation.constraints.NotEmpty;

public record AlunoRequestDTO(
    @NotEmpty Long num_matricula, 
    @NotEmpty String usuario, 
    @NotEmpty String senha) {
    
}
