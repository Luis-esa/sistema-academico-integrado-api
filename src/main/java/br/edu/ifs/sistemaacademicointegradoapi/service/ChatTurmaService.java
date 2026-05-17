package br.edu.ifs.sistemaacademicointegradoapi.service;

import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatTurmaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatTurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.ChatTurma;

import java.util.List;

public interface ChatTurmaService {
    ChatTurma buscarPorId(Long chatTurmaId);
    List<ChatTurmaResponseDTO> listarTodos();
    ChatTurmaResponseDTO buscarPorTurma(Long turmaId);
    ChatTurmaResponseDTO criar(ChatTurmaRequestDTO request, Long usuarioIdAdministrador);
    void inativar(Long id, Long usuarioIdAdministrador);
}
