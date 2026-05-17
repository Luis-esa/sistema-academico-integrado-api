package br.edu.ifs.sistemaacademicointegradoapi.controller;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.PeriodoLetivoRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.PeriodoLetivoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.service.PeriodoLetivoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periodos-letivos")
@RequiredArgsConstructor
public class PeriodoLetivoController {

    private final PeriodoLetivoService periodoLetivoService;

    @GetMapping
    public ResponseEntity<List<PeriodoLetivoResponseDTO>> listarPeriodos(@RequestParam Long usuarioIdRequisitante) {
        return ResponseEntity.ok(periodoLetivoService.listarTodos(usuarioIdRequisitante));
    }

    @PostMapping
    public ResponseEntity<PeriodoLetivoResponseDTO> criarPeriodo(
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid PeriodoLetivoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(periodoLetivoService.criar(request, usuarioIdRequisitante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeriodoLetivoResponseDTO> editarPeriodo(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid PeriodoLetivoRequestDTO request) {
        return ResponseEntity.ok(periodoLetivoService.editar(id, request, usuarioIdRequisitante));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarPeriodo(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante) {
        periodoLetivoService.inativar(id, usuarioIdRequisitante);
        return ResponseEntity.noContent().build();
    }
}
