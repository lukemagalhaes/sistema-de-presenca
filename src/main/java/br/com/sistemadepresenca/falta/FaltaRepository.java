package br.com.sistemadepresenca.falta;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemadepresenca.aluno.Aluno;

public interface FaltaRepository extends JpaRepository<Falta, Long> {

    Collection<Falta> findByJustificativaContainingIgnoreCase(String falta);
    
    List<Falta> findByAluno(Aluno aluno);
}
