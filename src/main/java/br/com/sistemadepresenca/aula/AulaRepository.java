package br.com.sistemadepresenca.aula;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistemadepresenca.professor.Professor;

public interface AulaRepository extends JpaRepository<Aula, Long> {

    List<Aula> findByConteudoContainingIgnoreCase(String aula);

    List<Aula> findByProfessor(Professor professor);

}
