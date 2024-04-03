package br.com.sistemadepresenca.falta;

public record FaltaResponseDTO(Long id, String data, String justificativa) {
    public FaltaResponseDTO(Falta falta){
        this(falta.getId(), falta.getData(), falta.getJustificativa());
    }
}
