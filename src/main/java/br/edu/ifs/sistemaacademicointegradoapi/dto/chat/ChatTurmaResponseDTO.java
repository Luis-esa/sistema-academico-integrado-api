package br.edu.ifs.sistemaacademicointegradoapi.dto.chat;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.TurmaResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ChatTurmaResponseDTO {

    private Long id;
    private TurmaResumoResponseDTO turma;
    private String titulo;
    private StatusEnum statusEnum;
    private LocalDateTime dataCriacao;

}
