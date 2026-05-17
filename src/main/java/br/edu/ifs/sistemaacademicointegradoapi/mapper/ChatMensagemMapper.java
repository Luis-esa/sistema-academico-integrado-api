package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatMensagemResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.ChatMensagem;
import org.springframework.stereotype.Component;

@Component
public class ChatMensagemMapper {

    public ChatMensagemResponseDTO toResponseDTO(ChatMensagem chatMensagem) {
        if (chatMensagem == null) {
            return null;
        }

        UsuarioResumoResponseDTO remetenteResumo = null;
        if (chatMensagem.getUsuario() != null) {
            remetenteResumo = UsuarioResumoResponseDTO.builder()
                    .id(chatMensagem.getUsuario().getId())
                    .nome(chatMensagem.getUsuario().getNome())
                    .email(chatMensagem.getUsuario().getEmail())
                    .login(chatMensagem.getUsuario().getLogin())
                    .build();
        }

        return ChatMensagemResponseDTO.builder()
                .id(chatMensagem.getId())
                .chatTurmaId(chatMensagem.getChatTurma() != null ? chatMensagem.getChatTurma().getId() : null)
                .remetente(remetenteResumo)
                .mensagem(chatMensagem.getMensagem())
                .dataDeEnvio(chatMensagem.getDataDeEnvio())
                .statusEnum(chatMensagem.getStatusEnum())
                .build();
    }
}
