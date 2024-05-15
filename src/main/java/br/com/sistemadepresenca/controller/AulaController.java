package br.com.sistemadepresenca.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.sistemadepresenca.aluno.Aluno;
import br.com.sistemadepresenca.aula.Aula;
import br.com.sistemadepresenca.aula.AulaRepository;
import br.com.sistemadepresenca.aula.AulaRequestDTO;
import br.com.sistemadepresenca.aula.AulaResponseDTO;
import br.com.sistemadepresenca.falta.FaltaRepository;
import br.com.sistemadepresenca.professor.Professor;
import br.com.sistemadepresenca.professor.ProfessorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
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

@RestController
@RequestMapping("/api/aulas")
@Tag(name = "Controller Aula", description = "Métodos HTTP das Aulas")

public class AulaController {
   @Autowired
   private AulaRepository repository;
   @Autowired
   private ProfessorRepository professorRepository;

   @CrossOrigin(origins = "*", allowedHeaders = "*")
   @PostMapping
   @Operation(summary = "Salvar Aula", description = "Salva uma nova Aula.", tags = { "Aulas" })
   @ApiResponses({
         @ApiResponse(responseCode = "200", description = "Aula salva com sucesso"),
         @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
         @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
   public ResponseEntity<Void> saveAula(@RequestBody @Valid AulaRequestDTO data) {
      try {
         Aula AulaData = new Aula(data);
         repository.save(AulaData);
         return ResponseEntity.ok().build();
      } catch (Exception ex) {
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping
   @Operation(summary = "Listar Aula", description = "Retorna a lista de Aula cadastrados", tags = {
         "Aulas" })
   @ApiResponses({
         @ApiResponse(responseCode = "200", description = "Aula encontarada com sucesso"),
         @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
         @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
   public List<Aula> getAll() {
      List<Aula> listaAula = repository.findAll();
      return listaAula;
   }

   @PutMapping("/{id}")
   @Operation(summary = "Atualizar Aula", description = "Atualiza um Aula da lista passando o ID //como parâmetro.", tags = {
         "Aulas" })
   @ApiResponses({
         @ApiResponse(responseCode = "200", description = "Aula atualizada com sucesso"),
         @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
         @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
   public ResponseEntity<Aula> updateAula(@RequestBody Aula data) {
      try {
         if (data.getId_aula() > 0) {
            Aula updateAula = repository.save(data);
            return ResponseEntity.ok(updateAula);
         } else {
            return ResponseEntity.badRequest().build();
         }
      } catch (Exception ex) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
      }
   }

   @DeleteMapping("/{id}")
   @Operation(summary = "Deletar Aula", description = "Manda uma requisição que apaga um Aula //passado como parâmetro", tags = {
         "Aula" })
   @ApiResponses({
         @ApiResponse(responseCode = "200", description = "Aula apagada com sucesso"),
         @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
         @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
   public ResponseEntity<Void> deleteAula(@PathVariable Long id) {
      try {
         repository.deleteById(id);
         return ResponseEntity.ok().build();
      } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }

   @GetMapping("/{id}")
   @Operation(summary = "Consultar Aula específico", description = "Consulta na lista de Aulas o //ID passado como parâmetro", tags = {
         "Aulas" })
   @ApiResponses({
         @ApiResponse(responseCode = "200", description = "Aula específico buscado com sucesso"),
         @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
         @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
   public ResponseEntity<AulaResponseDTO> getAulaById(@PathVariable Long id) {
      try {
         Aula Aula = repository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
         return ResponseEntity.ok(new AulaResponseDTO(Aula));
      } catch (Exception ex) {
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping("/buscar")
   @Operation(summary = "Buscar Aula", description = "Busca pelo nome e retorna suas informações.", tags = { "Aulas" })
   @ApiResponses({
         @ApiResponse(responseCode = "200", description = "Aula buscado com sucesso"),
         @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
         @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
   public ResponseEntity<List<AulaResponseDTO>> buscarPorNome(@RequestParam String Aula) {
      try {
         List<AulaResponseDTO> AulaList = repository.findByConteudoContainingIgnoreCase(Aula)
               .stream()
               .map(AulaResponseDTO::new)
               .collect(Collectors.toList());
         return ResponseEntity.ok().body(AulaList);
      } catch (Exception ex) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }

   @GetMapping("/por-professor/{professorId}")
   @Operation(summary = "Buscar Aula por Professor", description = "Busca as aulas associadas a um professor pelo seu ID.", tags = { "Aulas" })
   @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Aulas encontradas com sucesso"),
      @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
      @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
   public ResponseEntity<List<Aula>> getAulasByProfessorId(@PathVariable Long professorId) {
      try {
         Optional<Professor> professor = professorRepository.findById(professorId);
         if (professor.isPresent()) {
               List<Aula> aulas = repository.findByProfessor(professor.get());
               if (aulas.isEmpty()) {
                  return ResponseEntity.notFound().build();
               }
               return ResponseEntity.ok(aulas);
         } else {
               return ResponseEntity.notFound().build(); // Professor não encontrado
         }
      } catch (Exception ex) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }

}
