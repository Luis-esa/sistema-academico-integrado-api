package br.edu.ifs.sistemaacademicointegradoapi.controller;

import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.NotaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.NotaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.service.NotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;

    @GetMapping
    public ResponseEntity<List<NotaResponseDTO>> listarNotas(@RequestParam Long usuarioIdRequisitante) {
        return ResponseEntity.ok(notaService.listarTodos(usuarioIdRequisitante));
    }

    @GetMapping("/matricula/{matriculaTurmaId}")
    public ResponseEntity<List<NotaResponseDTO>> listarNotasDaMatricula(@PathVariable Long matriculaTurmaId) {
        return ResponseEntity.ok(notaService.listarNotasDaMatricula(matriculaTurmaId));
    }

    @PostMapping
    public ResponseEntity<NotaResponseDTO> criarNota(
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid NotaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notaService.criar(request, usuarioIdRequisitante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaResponseDTO> editarNota(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid NotaRequestDTO request) {
        return ResponseEntity.ok(notaService.editar(id, request, usuarioIdRequisitante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNota(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante) {
        // Exclusão física para a Nota
        notaService.deletar(id, usuarioIdRequisitante);
        return ResponseEntity.noContent().build();
    }
}
