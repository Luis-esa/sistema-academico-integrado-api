package br.edu.ifs.sistemaacademicointegradoapi.controller;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.CursoRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.CursoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> listarCursos(@RequestParam Long usuarioIdRequisitante) {
        return ResponseEntity.ok(cursoService.listarTodos(usuarioIdRequisitante));
    }

    @PostMapping
    public ResponseEntity<CursoResponseDTO> criarCurso(
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid CursoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.criar(request, usuarioIdRequisitante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> editarCurso(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid CursoRequestDTO request) {
        return ResponseEntity.ok(cursoService.editar(id, request, usuarioIdRequisitante));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarCurso(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante) {
        cursoService.inativar(id, usuarioIdRequisitante);
        return ResponseEntity.noContent().build();
    }
}
