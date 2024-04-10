package br.com.sistemadepresenca.aula;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AulaRepository extends JpaRepository<Aula, Long>{

    Collection<Aula> findByConteudoContainingIgnoreCase(String aula);

    
}
