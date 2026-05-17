package br.edu.ifs.sistemaacademicointegradoapi.controller;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.DisciplinaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.DisciplinaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.service.DisciplinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDTO>> listarDisciplinas(@RequestParam Long usuarioIdRequisitante) {
        return ResponseEntity.ok(disciplinaService.listarTodos(usuarioIdRequisitante));
    }

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> criarDisciplina(
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid DisciplinaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaService.criar(request, usuarioIdRequisitante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> editarDisciplina(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid DisciplinaRequestDTO request) {
        return ResponseEntity.ok(disciplinaService.editar(id, request, usuarioIdRequisitante));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarDisciplina(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante) {
        disciplinaService.inativar(id, usuarioIdRequisitante);
        return ResponseEntity.noContent().build();
    }
}
