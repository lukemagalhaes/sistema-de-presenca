package br.com.sistemadepresenca.aluno;

import jakarta.persistence.*;

@Table(name="alunos")
@Entity(name="alunos")
public class Aluno {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tia;
    private String nome;
}
