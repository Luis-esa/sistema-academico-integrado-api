package br.edu.ifs.sistemaacademicointegradoapi.controller;

import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatMensagemRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatMensagemResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.service.ChatMensagemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-mensagens")
@RequiredArgsConstructor
public class ChatMensagemController {

    private final ChatMensagemService chatMensagemService;

    @GetMapping("/chat/{chatTurmaId}")
    public ResponseEntity<List<ChatMensagemResponseDTO>> listarMensagensDoChat(
            @PathVariable Long chatTurmaId,
            @RequestParam Long usuarioIdRequisitante) {
        return ResponseEntity.ok(chatMensagemService.listarMensagensPorChat(chatTurmaId, usuarioIdRequisitante));
    }

    @PostMapping
    public ResponseEntity<ChatMensagemResponseDTO> enviarMensagem(
            @RequestParam Long usuarioIdRequisitante,
            @RequestBody @Valid ChatMensagemRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chatMensagemService.enviarMensagem(request, usuarioIdRequisitante));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarMensagem(
            @PathVariable Long id,
            @RequestParam Long usuarioIdRequisitante) {
        chatMensagemService.inativarMensagem(id, usuarioIdRequisitante);
        return ResponseEntity.noContent().build();
    }
}
