package br.com.sistemadepresenca.professor;

public record ProfessorResponseDTO(Long id_professor, String nome, String disciplina, String formacao) {
    public ProfessorResponseDTO(Professor professor) {
        this(professor.getId_professor(), professor.getNome(), professor.getDisciplina(), professor.getFormacao());
    }
}
