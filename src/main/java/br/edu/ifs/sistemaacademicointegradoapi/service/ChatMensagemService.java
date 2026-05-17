package br.edu.ifs.sistemaacademicointegradoapi.service;

import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatMensagemRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatMensagemResponseDTO;

import java.util.List;

public interface ChatMensagemService {
    List<ChatMensagemResponseDTO> listarMensagensPorChat(Long chatTurmaId, Long usuarioIdReq);
    ChatMensagemResponseDTO enviarMensagem(ChatMensagemRequestDTO request, Long usuarioIdReq);
    void inativarMensagem(Long id, Long usuarioIdReq);
}
