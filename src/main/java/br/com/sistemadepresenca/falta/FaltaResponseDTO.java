package br.com.sistemadepresenca.falta;

import br.com.sistemadepresenca.aluno.Aluno;
import br.com.sistemadepresenca.aula.Aula;

public record FaltaResponseDTO(Long id_presenca, Aluno aluno, Aula aula, boolean presenca, String justificativa) {
    public FaltaResponseDTO(Falta falta){
        this(falta.getId_presenca(), falta.getAluno(), falta.getAula(), falta.isPresenca(), falta.getJustificativa());
    }
}
