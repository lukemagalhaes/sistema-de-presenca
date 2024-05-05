package br.com.sistemadepresenca.turma;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name = "turma")
@Entity
@Getter
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_turma;

    @Column(name = "ano_ensino")
    private int anoEnsino;

    private String serie;
    private String periodo;

    public Turma() {

    }

    public Turma(TurmaRequestDTO data) {
        this.anoEnsino = data.anoEnsino();
        this.serie = data.serie();
        this.periodo = data.periodo();
    }
}
