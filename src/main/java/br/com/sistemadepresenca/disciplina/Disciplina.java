package br.com.sistemadepresenca.disciplina;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="disciplinas")
@Entity(name="disciplinas")
@Getter
@NoArgsConstructor

public class Disciplina {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private int horario;
    private int turma;

    public Disciplina(DisciplinaRequestDTO data){
        this.horario = data.horario();
        this.turma = data.turma();
    }

}


// public class Aluno {

//     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long tia;
//     private String nome;
// }