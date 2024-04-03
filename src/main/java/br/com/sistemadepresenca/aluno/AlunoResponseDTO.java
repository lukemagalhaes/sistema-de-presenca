package br.com.sistemadepresenca.aluno;

public record AlunoResponseDTO(Long id, Long num_matricula, String usuario, String senha) {
    public AlunoResponseDTO(Aluno aluno){
        this(aluno.getId(), aluno.getNum_matricula(), aluno.getUsuario(), aluno.getSenha());
    }
}
