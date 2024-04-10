package br.com.sistemadepresenca.aula;
import java.sql.Date;

import br.com.sistemadepresenca.professor.Professor;
import br.com.sistemadepresenca.turma.Turma;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="aula")
@Entity(name="aula")
@Getter
@NoArgsConstructor

public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_aula;

    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;

    private Date data;
    private String tipo;
    private String conteudo;

    public Aula(AulaRequestDTO data){
        this.turma = data.turma();
        this.professor = data.professor();
        this.data = data.data();
        this.tipo = data.tipo();
        this.conteudo = data.conteudo();
    }

}
