package br.edu.ifs.sistemaacademicointegradoapi.dto.chat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatTurmaRequestDTO {

    @NotNull(message = "A Turma deve ser informada para criar um chat!")
    private Long turmaId;

    private String titulo;

}
