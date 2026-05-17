package br.edu.ifs.sistemaacademicointegradoapi.controller;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.ProfessorRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.ProfessorResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.service.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> listarProfessores(@RequestParam Long usuarioIdRequisitante) {
        return ResponseEntity.ok(professorService.listarTodos(usuarioIdRequisitante));
    }

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> criarProfessor(
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid ProfessorRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.criar(request, usuarioIdRequisitante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> editarProfessor(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid ProfessorRequestDTO request) {
        return ResponseEntity.ok(professorService.editar(id, request, usuarioIdRequisitante));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarProfessor(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante) {
        professorService.inativar(id, usuarioIdRequisitante);
        return ResponseEntity.noContent().build();
    }
}
