package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatTurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.TurmaResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.ChatTurma;
import org.springframework.stereotype.Component;

@Component
public class ChatTurmaMapper {

    public ChatTurmaResponseDTO toResponseDTO(ChatTurma chatTurma) {
        if (chatTurma == null) {
            return null;
        }

        TurmaResumoResponseDTO turmaResumo = null;
        if (chatTurma.getTurma() != null) {
            turmaResumo = TurmaResumoResponseDTO.builder()
                    .id(chatTurma.getTurma().getId())
                    .descricao(chatTurma.getTurma().getDescricao())
                    .disciplinaNome(chatTurma.getTurma().getDisciplina() != null ? chatTurma.getTurma().getDisciplina().getNome() : null)
                    .professorNome(chatTurma.getTurma().getProfessor() != null && chatTurma.getTurma().getProfessor().getUsuario() != null ? chatTurma.getTurma().getProfessor().getUsuario().getNome() : null)
                    .build();
        }

        return ChatTurmaResponseDTO.builder()
                .id(chatTurma.getId())
                .turma(turmaResumo)
                .titulo(chatTurma.getTitulo())
                .statusEnum(chatTurma.getStatusEnum())
                .dataCriacao(chatTurma.getDataCriacao())
                .build();
    }
}
