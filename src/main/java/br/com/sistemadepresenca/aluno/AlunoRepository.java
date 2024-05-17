package br.com.sistemadepresenca.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sistemadepresenca.turma.Turma;

import java.util.Collection;
import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Collection<Aluno> findByNomeContainingIgnoreCase(String aluno);

    List<Aluno> findByTurma(Turma turma);
}
