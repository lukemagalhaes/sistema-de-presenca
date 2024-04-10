package br.com.sistemadepresenca.turma;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name="Turmas")
@Entity(name="Turmas")
@Getter

public class Turma {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String anoEnsino;

    public Turma(TurmaRequestDTO data){
        this.anoEnsino = data.anoEnsino();
    }
}