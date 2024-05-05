package br.com.sistemadepresenca.aluno;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Collection<Aluno> findByNomeContainingIgnoreCase(String aluno);

}