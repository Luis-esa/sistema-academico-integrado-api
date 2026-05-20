package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatMensagemResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.ChatMensagem;
import org.springframework.stereotype.Component;

@Component
public class ChatMensagemMapper {

    private final UsuarioMapper usuarioMapper;

    public ChatMensagemMapper(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    public ChatMensagemResponseDTO toResponseDTO(ChatMensagem chatMensagem) {
        if (chatMensagem == null) {
            return null;
        }

        UsuarioResponseDTO remetente = usuarioMapper.toResponseDTO(chatMensagem.getUsuario());

        return ChatMensagemResponseDTO.builder()
                .id(chatMensagem.getId())
                .chatTurmaId(chatMensagem.getChatTurma() != null ? chatMensagem.getChatTurma().getId() : null)
                .remetente(remetente)
                .mensagem(chatMensagem.getMensagem())
                .dataDeEnvio(chatMensagem.getDataDeEnvio())
                .statusEnum(chatMensagem.getStatusEnum())
                .build();
    }
}
