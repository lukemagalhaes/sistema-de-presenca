package br.com.sistemadepresenca.turma;

public record TurmaResponseDTO(Long id, String anoEnsino) {
    public TurmaResponseDTO(Turma turma){
        this(turma.getId(), turma.getAnoEnsino());
    }
}
