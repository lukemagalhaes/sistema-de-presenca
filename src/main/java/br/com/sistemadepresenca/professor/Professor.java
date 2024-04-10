package br.com.sistemadepresenca.professor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name="professor")
@Entity(name="professor")
@Getter

public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_professor;

    private String nome;
    private String disciplina;
    private String formacao;

    public Professor(ProfessorRequestDTO data){
        this.nome = data.nome();
        this.disciplina = data.disciplina();
        this.formacao = data.formacao();
    }
}
