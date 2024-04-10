package br.com.sistemadepresenca.notificacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name="Notificacoes")
@Entity(name="Notificacoes")
@Getter

public class Notificacao {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String mensagem;

    public Notificacao(NotificacaoRequestDTO data){
        this.mensagem = data.mensagem();
    }
}
