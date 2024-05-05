package br.com.sistemadepresenca.falta;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FaltaRepository extends JpaRepository<Falta, Long> {

    Collection<Falta> findByJustificativaContainingIgnoreCase(String falta);

}
