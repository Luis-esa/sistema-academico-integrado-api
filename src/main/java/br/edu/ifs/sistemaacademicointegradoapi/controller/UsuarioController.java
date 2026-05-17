package br.edu.ifs.sistemaacademicointegradoapi.controller;

import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios(@RequestParam Long usuarioIdRequisitante) {
        return ResponseEntity.ok(usuarioService.listarTodos(usuarioIdRequisitante));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody @Valid UsuarioRequestDTO request) {
        // Para a entidade Usuario, o ID do administrador já está contido dentro do UsuarioRequestDTO
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.adicionar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> editarUsuario(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioRequestDTO request) {
        return ResponseEntity.ok(usuarioService.editar(id, request));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarUsuario(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante) {
        usuarioService.inativar(id, usuarioIdRequisitante);
        return ResponseEntity.noContent().build();
    }
}
