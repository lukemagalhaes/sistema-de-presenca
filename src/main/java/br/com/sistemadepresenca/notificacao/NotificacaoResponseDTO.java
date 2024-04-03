package br.com.sistemadepresenca.notificacao;

public record NotificacaoResponseDTO(Long id, String mensagem) {
    public NotificacaoResponseDTO(Notificacao notificacao){
        this(notificacao.getId(), notificacao.getMensagem());
    }
}
