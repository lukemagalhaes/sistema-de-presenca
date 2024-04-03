package br.com.sistemadepresenca.aluno;

import jakarta.persistence.*;

@Table(name="alunos")
@Entity(name="alunos")
public class Aluno {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num_matricula;
    private String usuario;
    private String senha;

    
}
