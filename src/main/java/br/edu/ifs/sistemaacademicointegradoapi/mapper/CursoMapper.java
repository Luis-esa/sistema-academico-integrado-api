package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.CursoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoResponseDTO toResponseDTO(Curso curso) {
        if (curso == null) {
            return null;
        }

        return CursoResponseDTO.builder()
                .id(curso.getId())
                .nome(curso.getNome())
                .codigoSuap(curso.getCodigoSuap())
                .statusEnum(curso.getStatus())
                .build();
    }
}
