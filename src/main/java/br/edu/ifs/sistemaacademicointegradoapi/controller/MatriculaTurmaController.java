package br.edu.ifs.sistemaacademicointegradoapi.controller;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.service.MatriculaTurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
public class MatriculaTurmaController {

    private final MatriculaTurmaService matriculaTurmaService;

    @GetMapping
    public ResponseEntity<List<MatriculaTurmaResponseDTO>> listarMatriculas(@RequestParam Long usuarioIdRequisitante) {
        return ResponseEntity.ok(matriculaTurmaService.listarTodos(usuarioIdRequisitante));
    }

    @GetMapping("/turma/{turmaId}/alunos")
    public ResponseEntity<List<MatriculaTurmaResponseDTO>> listarAlunosDaTurma(@PathVariable Long turmaId) {
        return ResponseEntity.ok(matriculaTurmaService.listarAlunosPorTurma(turmaId));
    }

    @GetMapping("/aluno/{alunoId}/disciplinas")
    public ResponseEntity<List<MatriculaTurmaResponseDTO>> listarDisciplinasDoAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok(matriculaTurmaService.listarDisciplinasDoAluno(alunoId));
    }

    @PostMapping
    public ResponseEntity<MatriculaTurmaResponseDTO> criarMatricula(
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid MatriculaTurmaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaTurmaService.criar(request, usuarioIdRequisitante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaTurmaResponseDTO> editarMatricula(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid MatriculaTurmaRequestDTO request) {
        return ResponseEntity.ok(matriculaTurmaService.editar(id, request, usuarioIdRequisitante));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarMatricula(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante) {
        matriculaTurmaService.inativar(id, usuarioIdRequisitante);
        return ResponseEntity.noContent().build();
    }
}
