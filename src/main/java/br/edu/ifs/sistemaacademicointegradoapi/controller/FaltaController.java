package br.edu.ifs.sistemaacademicointegradoapi.controller;

import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.FaltaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.FaltaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.service.FaltaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faltas")
@RequiredArgsConstructor
public class FaltaController {

    private final FaltaService faltaService;

    @GetMapping
    public ResponseEntity<List<FaltaResponseDTO>> listarFaltas(@RequestParam Long usuarioIdRequisitante) {
        return ResponseEntity.ok(faltaService.listarTodos(usuarioIdRequisitante));
    }

    @GetMapping("/matricula/{matriculaTurmaId}")
    public ResponseEntity<List<FaltaResponseDTO>> listarFaltasDaMatricula(@PathVariable Long matriculaTurmaId) {
        return ResponseEntity.ok(faltaService.listarFaltasDaMatricula(matriculaTurmaId));
    }

    @PostMapping
    public ResponseEntity<FaltaResponseDTO> criarFalta(
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid FaltaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(faltaService.criar(request, usuarioIdRequisitante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaltaResponseDTO> editarFalta(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid FaltaRequestDTO request) {
        return ResponseEntity.ok(faltaService.editar(id, request, usuarioIdRequisitante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFalta(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante) {
        // Exclusão física para a Falta
        faltaService.deletar(id, usuarioIdRequisitante);
        return ResponseEntity.noContent().build();
    }
}
