package br.edu.ifs.sistemaacademicointegradoapi.dto.chat;

import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ChatMensagemResponseDTO {

    private Long id;
    private Long chatTurmaId;
    private UsuarioResponseDTO remetente;
    private String mensagem;
    private LocalDateTime dataDeEnvio;
    private StatusEnum statusEnum;

}
