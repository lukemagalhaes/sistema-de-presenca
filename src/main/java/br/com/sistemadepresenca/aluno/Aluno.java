package br.com.sistemadepresenca.aluno;

import jakarta.persistence.*;
import lombok.Getter;

@Table(name="alunos")
@Entity(name="alunos")
@Getter
public class Aluno {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long num_matricula;
    private String usuario;
    private String senha;

    
}
