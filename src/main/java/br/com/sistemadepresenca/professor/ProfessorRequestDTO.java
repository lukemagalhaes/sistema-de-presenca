package br.com.sistemadepresenca.professor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProfessorRequestDTO(
    @NotEmpty String usuario, 
    @NotEmpty @NotNull String senha, 
    @NotEmpty String perfil, 
    @NotEmpty String email) {
    
}
