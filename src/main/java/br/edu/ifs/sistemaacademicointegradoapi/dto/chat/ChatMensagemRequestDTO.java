package br.edu.ifs.sistemaacademicointegradoapi.dto.chat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatMensagemRequestDTO {

    @NotNull(message = "O ID do Chat deve ser informado!")
    private Long chatTurmaId;

    @NotBlank(message = "A mensagem não pode ser vazia!")
    private String mensagem;

}
