package br.com.sistemadepresenca.turma;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long>{

    Collection<Turma> findBySerieContainingIgnoreCase(String turma);
    
}
