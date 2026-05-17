package br.edu.ifs.sistemaacademicointegradoapi.controller;

import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatTurmaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatTurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.service.ChatTurmaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-turmas")
@RequiredArgsConstructor
public class ChatTurmaController {

    private final ChatTurmaService chatTurmaService;

    @GetMapping
    public ResponseEntity<List<ChatTurmaResponseDTO>> listarChats() {
        return ResponseEntity.ok(chatTurmaService.listarTodos());
    }

    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<ChatTurmaResponseDTO> buscarChatDaTurma(@PathVariable Long turmaId) {
        return ResponseEntity.ok(chatTurmaService.buscarPorTurma(turmaId));
    }

    @PostMapping
    public ResponseEntity<ChatTurmaResponseDTO> criarChat(
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid ChatTurmaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chatTurmaService.criar(request, usuarioIdRequisitante));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarChat(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante) {
        chatTurmaService.inativar(id, usuarioIdRequisitante);
        return ResponseEntity.noContent().build();
    }
}
