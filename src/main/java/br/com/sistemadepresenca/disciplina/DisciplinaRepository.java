package br.com.sistemadepresenca.disciplina;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{

    Collection<Disciplina> findByNomeContainingIgnoreCase(String disciplina);

    
}
