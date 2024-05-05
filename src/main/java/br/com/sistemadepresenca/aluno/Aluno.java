package br.com.sistemadepresenca.aluno;

import java.sql.Date;

import br.com.sistemadepresenca.turma.Turma;
import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "aluno")
@Entity(name = "aluno")
@Getter
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_aluno;

    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;

    private String nome;
    private int idade;
    private String genero;
    private Date dataNascimento;
    private String endereco;

    public Aluno() {
    }

    public Aluno(AlunoRequestDTO data) {
        this.nome = data.nome();
        this.idade = data.idade();
        this.genero = data.genero();
        this.dataNascimento = data.dataNascimento();
        this.endereco = data.endereco();
    }

}
