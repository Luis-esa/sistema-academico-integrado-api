package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.PeriodoLetivoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.PeriodoLetivo;
import org.springframework.stereotype.Component;

@Component
public class PeriodoLetivoMapper {

    public PeriodoLetivoResponseDTO toResponseDTO(PeriodoLetivo periodoLetivo) {
        if (periodoLetivo == null) {
            return null;
        }

        return PeriodoLetivoResponseDTO.builder()
                .id(periodoLetivo.getId())
                .ano(periodoLetivo.getAno())
                .semestre(periodoLetivo.getSemestre())
                .descricao(periodoLetivo.getDescricao())
                .statusEnum(periodoLetivo.getStatus())
                .build();
    }
}
