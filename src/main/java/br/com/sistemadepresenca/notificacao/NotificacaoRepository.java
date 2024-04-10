package br.com.sistemadepresenca.notificacao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    Collection<Notificacao> findByNomeContainingIgnoreCase(String notificacao);

}