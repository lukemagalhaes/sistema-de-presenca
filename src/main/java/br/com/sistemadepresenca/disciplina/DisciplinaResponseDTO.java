package br.com.sistemadepresenca.disciplina;

public record DisciplinaResponseDTO(Long id, int horario, int turma ) {
    public DisciplinaResponseDTO(Disciplina disciplina){
        this(disciplina.getId(), disciplina.getHorario(), disciplina.getTurma());
    }
}
