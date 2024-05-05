// package br.com.sistemadepresenca.controller;
//
// import java.util.List;
// import java.util.stream.Collectors;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.server.ResponseStatusException;
//
// import br.com.sistemadepresenca.notificacao.Notificacao;
// import br.com.sistemadepresenca.notificacao.NotificacaoRepository;
// import br.com.sistemadepresenca.notificacao.NotificacaoRequestDTO;
// import br.com.sistemadepresenca.notificacao.NotificacaoResponseDTO;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.parameters.RequestBody;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.validation.Valid;
//
// @RestController
// @RequestMapping("/api/notificacao")
// @Tag(name = "Controller notificacao", description = "Métodos HTTP das
// notificacao")
// public class NotificacaoController {
// @Autowired
// private NotificacaoRepository repository;
//
// @CrossOrigin(origins = "*", allowedHeaders = "*")
// @PostMapping
// @Operation(summary = "Salvar notificacao", description = "Salva uma nova
// notificacao.", tags = { //"Notificacoes" })
// @ApiResponses({
// @ApiResponse(responseCode = "200", description = "notificacao salva com
// sucesso"),
// @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
// @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
// })
// public ResponseEntity<Void> saveNotificacao(@RequestBody @Valid
// NotificacaoRequestDTO data) {
// try {
// Notificacao notificacaoData = new Notificacao(data);
// repository.save(notificacaoData);
// return ResponseEntity.ok().build();
// } catch (Exception ex) {
// return ResponseEntity.notFound().build();
// }
// }
//
// @GetMapping
// @Operation(summary = "Salvar notificacao", description = "Salva uma nova
// notificacao.", tags = { //"Notificacoes" })
// @ApiResponses({
// @ApiResponse(responseCode = "200", description = "notificacao salva com
// sucesso"),
// @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
// @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
// })
// public List<Notificacao> getAll(){
// List<Notificacao> listaNotificacao = repository.findAll();
// return listaNotificacao;
// }
//
// @PutMapping("/{id}")
// @Operation(summary = "Salvar notificacao", description = "Salva uma nova
// notificacao.", tags = { //"Notificacoes" })
// @ApiResponses({
// @ApiResponse(responseCode = "200", description = "notificacao salva com
// sucesso"),
// @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
// @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
// })
// public ResponseEntity<Notificacao> updateNotificacao(@RequestBody Notificacao
// data) {
// try {
// if (data.getId() > 0) {
// Notificacao updateNotificacao = repository.save(data);
// return ResponseEntity.ok(updateNotificacao);
// } else {
// return ResponseEntity.badRequest().build();
// }
// } catch (Exception ex) {
// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
// }
// }
//
// @DeleteMapping("/{id}")
// @Operation(summary = "Salvar notificacao", description = "Salva uma nova
// notificacao.", tags = { //"Notificacoes" })
// @ApiResponses({
// @ApiResponse(responseCode = "200", description = "notificacao salva com
// sucesso"),
// @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
// @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
// })
// public ResponseEntity<Void> deleteNotificacao(@PathVariable Long id) {
// try {
// repository.deleteById(id);
// return ResponseEntity.ok().build();
// } catch (Exception e) {
// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
// }
// }
//
// @GetMapping("/{id}")
// @Operation(summary = "Salvar notificacao", description = "Salva uma nova
// notificacao.", tags = { //"Notificacoes" })
// @ApiResponses({
// @ApiResponse(responseCode = "200", description = "notificacao salva com
// sucesso"),
// @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
// @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
// })
// public ResponseEntity<NotificacaoResponseDTO>
// getNotificacaoById(@PathVariable Long id) {
// try {
// Notificacao notificacao = repository.findById(id)
// .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
// return ResponseEntity.ok(new NotificacaoResponseDTO(notificacao));
// } catch (Exception ex) {
// return ResponseEntity.notFound().build();
// }
// }
//
// @GetMapping("/buscar")
// @Operation(summary = "Salvar notificacao", description = "Salva uma nova
// notificacao.", tags = { //"Notificacoes" })
// @ApiResponses({
// @ApiResponse(responseCode = "200", description = "notificacao salva com
// sucesso"),
// @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
// @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
// })
// public ResponseEntity<List<NotificacaoResponseDTO>>
// buscarPorNome(@RequestParam String notificacao) {
// try {
// List<NotificacaoResponseDTO> notificacaoList =
// repository.findByNomeContainingIgnoreCase//(notificacao)
// .stream()
// .map(NotificacaoResponseDTO::new)
// .collect(Collectors.toList());
// return ResponseEntity.ok().body(notificacaoList);
// } catch (Exception ex) {
// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
// }
// }
// }
