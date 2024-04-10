package br.com.sistemadepresenca.professor;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Collection<Professor> findByNomeContainingIgnoreCase(String professor);

    
} 
