package br.edu.ifs.sistemaacademicointegradoapi.controller;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.AlunoRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.AlunoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listarAlunos(@RequestParam Long usuarioIdRequisitante) {
        return ResponseEntity.ok(alunoService.listarTodos(usuarioIdRequisitante));
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criarAluno(
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid AlunoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.criar(request, usuarioIdRequisitante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> editarAluno(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid AlunoRequestDTO request) {
        return ResponseEntity.ok(alunoService.editar(id, request, usuarioIdRequisitante));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarAluno(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante) {
        alunoService.inativar(id, usuarioIdRequisitante);
        return ResponseEntity.noContent().build();
    }
}
