package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.FaltaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Falta;
import org.springframework.stereotype.Component;

@Component
public class FaltaMapper {

    private final MatriculaTurmaMapper matriculaTurmaMapper;

    public FaltaMapper(MatriculaTurmaMapper matriculaTurmaMapper) {
        this.matriculaTurmaMapper = matriculaTurmaMapper;
    }

    public FaltaResponseDTO toResponseDTO(Falta falta) {
        if (falta == null) {
            return null;
        }

        MatriculaTurmaResponseDTO matriculaTurma = matriculaTurmaMapper.toResponseDTO(falta.getMatriculaTurma());

        return FaltaResponseDTO.builder()
                .id(falta.getId())
                .matriculaTurma(matriculaTurma)
                .dataAula(falta.getDataAula())
                .quantidadeFaltas(falta.getQuantidadeFaltas())
                .justificativa(falta.getJustificativa())
                .codigoSuap(falta.getCodigoSuap())
                .build();
    }
}
