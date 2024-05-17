package br.com.sistemadepresenca.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.sistemadepresenca.aluno.Aluno;
import br.com.sistemadepresenca.aluno.AlunoRepository;
import br.com.sistemadepresenca.aluno.AlunoRequestDTO;
import br.com.sistemadepresenca.aluno.AlunoResponseDTO;
import br.com.sistemadepresenca.aula.Aula;
import br.com.sistemadepresenca.professor.Professor;
import br.com.sistemadepresenca.professor.ProfessorRepository;
import br.com.sistemadepresenca.turma.Turma;
import br.com.sistemadepresenca.turma.TurmaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alunos")
@Tag(name = "Controller Aluno", description = "Métodos HTTP dos alunos")

public class AlunoController {
    @Autowired
    private AlunoRepository repository;
    @Autowired
    private TurmaRepository turmaRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Operation(summary = "Salvar aluno", description = "Salva um novo aluno.", tags = { "Alunos" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Aluno salvo com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<Void> saveAluno(@RequestBody @Valid AlunoRequestDTO data) {
        try {
            Aluno alunoData = new Aluno(data);
            repository.save(alunoData);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(summary = "Listar alunos", description = "Retorna a lista de alunos cadastrados", tags = {
            "alunos" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "aluno encontarado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public List<Aluno> getAll() {
        List<Aluno> listaAluno = repository.findAll();
        return listaAluno;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aluno", description = "Atualiza um aluno da lista passando o ID como parâmetro.", tags = {
            "Alunos" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<Aluno> updateAluno(@RequestBody Aluno data) {
        try {
            if (data.getId_aluno() > 0) {
                Aluno updateAluno = repository.save(data);
                return ResponseEntity.ok(updateAluno);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar aluno", description = "Manda uma requisição que apaga um aluno passado como parâmetro", tags = {
            "Alunos" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Aluno apagado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id_aluno) {
        try {
            repository.deleteById(id_aluno);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar aluno específico", description = "Consulta na lista de alunos o ID passado como parâmetro", tags = {
            "Alunos" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Aluno específico buscado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<AlunoResponseDTO> getAlunoById(@PathVariable("id") Long id_aluno) {
        try {
            Aluno aluno = repository.findById(id_aluno)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            return ResponseEntity.ok(new AlunoResponseDTO(aluno));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar aluno", description = "Busca pelo nome e retorna suas informações.", tags = {
            "Alunos" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Aluno buscado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<List<AlunoResponseDTO>> buscarPorNome(@RequestParam String aluno) {
        try {
            List<AlunoResponseDTO> alunoList = repository.findByNomeContainingIgnoreCase(aluno)
                    .stream()
                    .map(AlunoResponseDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(alunoList);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/turma/{turmaId}")
    @Operation(summary = "Listar alunos por turma", description = "Retorna a lista de alunos cadastrados em uma turma específica", tags = {
            "Alunos" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Alunos encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
    public ResponseEntity<List<Aluno>> getAlunosByTurmaId(@PathVariable Long turmaId){
        try {
            Optional<Turma> turma = turmaRepository.findById(turmaId);
            if (turma.isPresent()) {
                List<Aluno> alunos = repository.findByTurma(turma.get());
                if (alunos.isEmpty()) {
                   return ResponseEntity.notFound().build();
                }
                return ResponseEntity.ok(alunos);
          } else {
                return ResponseEntity.notFound().build();
          }
       } catch (Exception ex) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
}