package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatTurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.TurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.ChatTurma;
import org.springframework.stereotype.Component;

@Component
public class ChatTurmaMapper {

    private final TurmaMapper turmaMapper;

    public ChatTurmaMapper(TurmaMapper turmaMapper) {
        this.turmaMapper = turmaMapper;
    }

    public ChatTurmaResponseDTO toResponseDTO(ChatTurma chatTurma) {
        if (chatTurma == null) {
            return null;
        }

        TurmaResponseDTO turma = turmaMapper.toResponseDTO(chatTurma.getTurma());

        return ChatTurmaResponseDTO.builder()
                .id(chatTurma.getId())
                .turma(turma)
                .titulo(chatTurma.getTitulo())
                .statusEnum(chatTurma.getStatusEnum())
                .dataCriacao(chatTurma.getDataCriacao())
                .build();
    }
}
