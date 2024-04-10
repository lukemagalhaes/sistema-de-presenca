package br.com.sistemadepresenca.falta;

import br.com.sistemadepresenca.aluno.Aluno;
import br.com.sistemadepresenca.aula.Aula;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Table(name="falta")
@Entity(name="falta")

public class Falta {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_presenca;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_aula")
    private Aula aula;

    private boolean presenca;
    private String justificativa;

    public Falta(FaltaRequestDTO data){
        this.aluno = data.aluno();
        this.aula = data.aula();
        this.presenca = data.presenca();
        this.justificativa = data.justificativa();
    }
}
