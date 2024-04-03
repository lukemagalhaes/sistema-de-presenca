package br.com.sistemadepresenca.notificacao;

import jakarta.validation.constraints.NotEmpty;

public record NotificacaoRequestDTO(
    @NotEmpty String mensagem) {
    
}
