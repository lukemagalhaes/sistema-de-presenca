package br.com.sistemadepresenca.falta;

import jakarta.validation.constraints.NotEmpty;

public record FaltaRequestDTO(
    @NotEmpty String data, 
    
    @NotEmpty String justificativa) {
    
}
