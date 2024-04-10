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

import br.com.sistemadepresenca.professor.Professor;
import br.com.sistemadepresenca.professor.ProfessorRepository;
import br.com.sistemadepresenca.professor.ProfessorRequestDTO;
import br.com.sistemadepresenca.professor.ProfessorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/professor")
@Tag(name = "Controller professor", description = "Métodos HTTP do professor")
public class ProfessorController {
    @Autowired
    private ProfessorRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Operation(summary = "Salvar professor", description = "Salva uma nova professor.", tags = { "Professor" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Professor salva com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<Void> saveProfessor(@RequestBody @Valid ProfessorRequestDTO data) {
        try {
            Professor professorData = new Professor(data);
            repository.save(professorData);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(summary = "Salvar professor", description = "Salva uma nova professor.", tags = { "Professor" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Professor salva com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public List<Professor> getAll(){
        List<Professor>  listaProfessor = repository.findAll();
        return listaProfessor;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Salvar professor", description = "Salva uma nova professor.", tags = { "Professor" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Professor salva com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<Professor> updateProfessor(@RequestBody Professor data) {
        try {
            if (data.getId_professor() > 0) {
                Professor updateProfessor = repository.save(data);
                return ResponseEntity.ok(updateProfessor);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Salvar professor", description = "Salva uma nova professor.", tags = { "Professor" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Professor salva com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Salvar professor", description = "Salva uma nova professor.", tags = { "Professor" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Professor salva com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<ProfessorResponseDTO> getProfessorById(@PathVariable Long id) {
        try {
            Professor professor = repository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            return ResponseEntity.ok(new ProfessorResponseDTO(professor));
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    @Operation(summary = "Salvar professor", description = "Salva uma nova professor.", tags = { "Professor" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Professor salva com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<List<ProfessorResponseDTO>> buscarPorNome(@RequestParam String professor) {
        try {
            List<ProfessorResponseDTO> professorList = repository.findByNomeContainingIgnoreCase(professor)
                    .stream()
                    .map(ProfessorResponseDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(professorList);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
