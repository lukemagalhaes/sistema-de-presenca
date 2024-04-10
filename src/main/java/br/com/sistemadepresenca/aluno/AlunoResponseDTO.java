package br.com.sistemadepresenca.aluno;
import java.sql.Date;
import br.com.sistemadepresenca.turma.Turma;

public record AlunoResponseDTO(int id_aluno, Turma turma, String nome, int idade, String genero, Date dataNascimento, String endereco) {
    public AlunoResponseDTO(Aluno aluno){
        this(aluno.getId_aluno(), aluno.getTurma(), aluno.getNome(), aluno.getIdade(), aluno.getGenero(), aluno.getDataNascimento(), aluno.getEndereco());
    }
}