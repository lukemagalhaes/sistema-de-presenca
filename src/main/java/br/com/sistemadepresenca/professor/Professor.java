package br.com.sistemadepresenca.professor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Table(name="Professores")
@Entity(name="Professores")
@Getter

public class Professor {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String usuario;
    private String senha;
    private String perfil;
    private String email;
}
