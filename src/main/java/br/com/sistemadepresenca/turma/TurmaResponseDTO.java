package br.com.sistemadepresenca.turma;

public record TurmaResponseDTO(Long id, int anoEnsino, String serie, String periodo) {
    public TurmaResponseDTO(Turma turma) {
        this(turma.getId_turma(), turma.getAnoEnsino(), turma.getSerie(), turma.getPeriodo());
    }
}
