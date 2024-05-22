package br.com.sistemadepresenca.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.sistemadepresenca.turma.Turma;
import br.com.sistemadepresenca.turma.TurmaRepository;
import br.com.sistemadepresenca.turma.TurmaRequestDTO;
import br.com.sistemadepresenca.turma.TurmaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/turma")
@Tag(name = "Controller turma", description = "Métodos HTTP do turma")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class TurmaController {
    @Autowired
    private TurmaRepository repository;

   
    @PostMapping
    @Operation(summary = "Salvar turma", description = "Salva uma nova turma.", tags = { "Turma" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Turma salva com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<Void> saveTurma(@RequestBody @Valid TurmaRequestDTO data) {
        try {
            Turma turmaData = new Turma(data);
            repository.save(turmaData);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(summary = "Salvar turma", description = "Salva uma nova turma.", tags = { "Turma" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Turma salva com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public List<Turma> getAll() {
        List<Turma> listaTurma = repository.findAll();
        return listaTurma;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Salvar turma", description = "Salva uma nova turma.", tags = { "Turma" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Turma salva com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<Turma> updateTurma(@RequestBody Turma data) {
        try {
            if (data.getId_turma() > 0) {
                Turma updateTurma = repository.save(data);
                return ResponseEntity.ok(updateTurma);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Salvar turma", description = "Salva uma nova turma.", tags = { "Turma" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Turma salva com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<Void> deleteTurma(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Salvar turma", description = "Salva uma nova turma.", tags = { "Turma" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Turma salva com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<TurmaResponseDTO> getTurmaById(@PathVariable Long id) {
        try {
            Turma turma = repository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            return ResponseEntity.ok(new TurmaResponseDTO(turma));
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    @Operation(summary = "Salvar turma", description = "Salva uma nova turma.", tags = { "Turma" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Turma salva com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<List<TurmaResponseDTO>> buscarPorSerie(@RequestParam String serie) {
        try {
            List<TurmaResponseDTO> turmaList = repository.findBySerieContainingIgnoreCase(serie)
                    .stream()
                    .map(TurmaResponseDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(turmaList);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
