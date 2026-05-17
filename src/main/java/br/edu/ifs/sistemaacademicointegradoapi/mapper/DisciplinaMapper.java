package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.DisciplinaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Disciplina;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaMapper {

    public DisciplinaResponseDTO toResponseDTO(Disciplina disciplina) {
        if (disciplina == null) {
            return null;
        }

        return DisciplinaResponseDTO.builder()
                .id(disciplina.getId())
                .nome(disciplina.getNome())
                .codigo(disciplina.getCodigo())
                .codigoSuap(disciplina.getCodigoSuap())
                .cargaHoraria(disciplina.getCargaHoraria())
                .statusEnum(disciplina.getStatus())
                .build();
    }
}
