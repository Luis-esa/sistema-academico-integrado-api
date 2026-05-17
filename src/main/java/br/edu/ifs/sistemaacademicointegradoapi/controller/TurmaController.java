package br.edu.ifs.sistemaacademicointegradoapi.controller;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.TurmaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.TurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.service.TurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<TurmaResponseDTO>> listarTurmas(@RequestParam Long usuarioIdRequisitante) {
        return ResponseEntity.ok(turmaService.listarTodos(usuarioIdRequisitante));
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<TurmaResponseDTO>> listarTurmasDoProfessor(@PathVariable Long professorId) {
        // Como implementamos essa consulta específica sem exigir o requisitante no Service (para exemplificar), 
        // chamamos direto, ou você pode exigir o requisitante caso adicione validação depois.
        return ResponseEntity.ok(turmaService.listarTurmasDoProfessor(professorId));
    }

    @PostMapping
    public ResponseEntity<TurmaResponseDTO> criarTurma(
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid TurmaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.criar(request, usuarioIdRequisitante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> editarTurma(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid TurmaRequestDTO request) {
        return ResponseEntity.ok(turmaService.editar(id, request, usuarioIdRequisitante));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarTurma(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante) {
        turmaService.inativar(id, usuarioIdRequisitante);
        return ResponseEntity.noContent().build();
    }
}
