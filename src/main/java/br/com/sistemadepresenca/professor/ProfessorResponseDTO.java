package br.com.sistemadepresenca.professor;

public record ProfessorResponseDTO(Long id, String usuario, String senha, String perfil, String email) {
    public ProfessorResponseDTO(Professor professor){
        this(professor.getId(), professor.getUsuario(), professor.getSenha(), professor.getPerfil(), professor.getEmail());
    }
}
