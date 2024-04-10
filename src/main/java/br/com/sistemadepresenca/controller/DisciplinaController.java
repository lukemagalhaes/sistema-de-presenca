//package br.com.sistemadepresenca.controller;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import br.com.sistemadepresenca.disciplina.Disciplina;
//import br.com.sistemadepresenca.disciplina.DisciplinaRepository;
//import br.com.sistemadepresenca.disciplina.DisciplinaRequestDTO;
//import br.com.sistemadepresenca.disciplina.DisciplinaResponseDTO;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@RestController
//@RequestMapping("/api/disciplinas")
//@Tag(name = "Controller disciplina", description = "Métodos HTTP das disciplinas")
//
//public class DisciplinaController {
//    @Autowired
//    private DisciplinaRepository repository;
//
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PostMapping
//    @Operation(summary = "Salvar disciplina", description = "Salva uma nova disciplina.", tags = { //"Disciplinas" })
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "disciplina salva com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
//            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
//    public ResponseEntity<Void> saveDisciplina(@RequestBody @Valid DisciplinaRequestDTO data) {
//        try {
//            Disciplina disciplinaData = new Disciplina(data);
//            repository.save(disciplinaData);
//            return ResponseEntity.ok().build();
//        } catch (Exception ex) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping
//    @Operation(summary = "Listar disciplina", description = "Retorna a lista de disciplina cadastrados", tags //= {
//        "Disciplinas" })
//    @ApiResponses({
//        @ApiResponse(responseCode = "200", description = "disciplina encontarada com sucesso"),
//        @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
//        @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
//    public List<Disciplina> getAll(){
//        List<Disciplina>  listaDisciplina = repository.findAll();
//        return listaDisciplina;
//    }
//
//    @PutMapping("/{id}")
//    @Operation(summary = "Atualizar disciplina", description = "Atualiza um disciplina da lista passando o ID //como parâmetro.", tags = {
//            "Disciplinas" })
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Disciplina atualizada com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
//            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
//    public ResponseEntity<Disciplina> updateDisciplina(@RequestBody Disciplina data) {
//        try {
//            if (data.getId() > 0) {
//                Disciplina updateDisciplina = repository.save(data);
//                return ResponseEntity.ok(updateDisciplina);
//            } else {
//                return ResponseEntity.badRequest().build();
//            }
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Deletar disciplina", description = "Manda uma requisição que apaga um disciplina //passado como parâmetro", tags = {
//            "Disciplina" })
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Disciplina apagada com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
//            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
//    public ResponseEntity<Void> deleteDisciplina(@PathVariable Long id) {
//        try {
//            repository.deleteById(id);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "Consultar disciplina específico", description = "Consulta na lista de disciplinas o //ID passado como parâmetro", tags = {
//            "Disciplinas" })
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Disciplina específico buscado com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
//            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
//    public ResponseEntity<DisciplinaResponseDTO> getDisciplinaById(@PathVariable Long id) {
//        try {
//            Disciplina disciplina = repository.findById(id)
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
//            return ResponseEntity.ok(new DisciplinaResponseDTO(disciplina));
//        } catch (Exception ex) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/buscar")
//    @Operation(summary = "Buscar disciplina", description = "Busca pelo nome e retorna suas informações.", //tags = { "Disciplinas" })
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Disciplina buscado com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
//            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
//    public ResponseEntity<List<DisciplinaResponseDTO>> buscarPorNome(@RequestParam String disciplina) {
//        try {
//            List<DisciplinaResponseDTO> disciplinaList = repository.findByNomeContainingIgnoreCase(disciplina)
//                    .stream()
//                    .map(DisciplinaResponseDTO::new)
//                    .collect(Collectors.toList());
//            return ResponseEntity.ok().body(disciplinaList);
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//}
