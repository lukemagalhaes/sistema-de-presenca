//package br.com.sistemadepresenca.controller;
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
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import br.com.sistemadepresenca.falta.Falta;
//import br.com.sistemadepresenca.falta.FaltaRepository;
//import br.com.sistemadepresenca.falta.FaltaRequestDTO;
//import br.com.sistemadepresenca.falta.FaltaResponseDTO;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/api/faltas")
//@Tag(name = "Controller falta", description = "Métodos HTTP das faltas")
//public class FaltaController {
//    @Autowired
//    private FaltaRepository repository;
//
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PostMapping
//    @Operation(summary = "Salvar falta", description = "Salva uma nova falta.", tags = { "Faltas" })
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "falta salva com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
//            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
//    public ResponseEntity<Void> saveFalta(@RequestBody @Valid FaltaRequestDTO data) {
//        try {
//            Falta faltaData = new Falta(data);
//            repository.save(faltaData);
//            return ResponseEntity.ok().build();
//        } catch (Exception ex) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping
//    @Operation(summary = "Listar falta", description = "Retorna a lista de falta cadastrados", tags = {
//        "Faltas" })
//    @ApiResponses({
//        @ApiResponse(responseCode = "200", description = "Falta encontarada com sucesso"),
//        @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
//        @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
//    public List<Falta> getAll(){
//        List<Falta>  listaFalta = repository.findAll();
//        return listaFalta;
//    }
//
//    @PutMapping("/{id}")
//    @Operation(summary = "Atualizar falta", description = "Atualiza um falta da lista passando o ID como //parâmetro.", tags = {
//            "Faltas" })
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Falta atualizada com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
//            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
//    public ResponseEntity<Falta> updateFalta(@RequestBody Falta data) {
//        try {
//            if (data.getId() > 0) {
//                Falta updateFalta = repository.save(data);
//                return ResponseEntity.ok(updateFalta);
//            } else {
//                return ResponseEntity.badRequest().build();
//            }
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Deletar falta", description = "Manda uma requisição que apaga um falta passado como //parâmetro", tags = {
//            "Faltas" })
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Falta apagada com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
//            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
//    public ResponseEntity<Void> deleteFalta(@PathVariable Long id) {
//        try {
//            repository.deleteById(id);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "Consultar falta específico", description = "Consulta na lista de faltas o ID passado //como parâmetro", tags = {
//            "Faltas" })
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Falta específico buscado com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
//            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
//    public ResponseEntity<FaltaResponseDTO> getFaltaById(@PathVariable Long id) {
//        try {
//            Falta falta = repository.findById(id)
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
//            return ResponseEntity.ok(new FaltaResponseDTO(falta));
//        } catch (Exception ex) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/buscar")
//    @Operation(summary = "Buscar falta", description = "Busca pelo nome e retorna suas informações.", tags = { //"Faltas" })
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Falta buscado com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
//            @ApiResponse(responseCode = "500", description = "Erro interno no servidor") })
//    public ResponseEntity<List<FaltaResponseDTO>> buscarPorNome(@RequestParam String falta) {
//        try {
//            List<FaltaResponseDTO> faltaList = repository.findByNomeContainingIgnoreCase(falta)
//                    .stream()
//                    .map(FaltaResponseDTO::new)
//                    .collect(Collectors.toList());
//            return ResponseEntity.ok().body(faltaList);
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//}