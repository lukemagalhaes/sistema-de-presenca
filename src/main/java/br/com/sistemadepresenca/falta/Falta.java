package br.com.sistemadepresenca.falta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Table(name="Faltas")
@Entity(name="Faltas")

public class Falta {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String data;
    private String justificativa;
}
