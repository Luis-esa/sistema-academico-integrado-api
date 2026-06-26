package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.NotaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    private final MatriculaTurmaMapper matriculaTurmaMapper;

    public NotaMapper(MatriculaTurmaMapper matriculaTurmaMapper) {
        this.matriculaTurmaMapper = matriculaTurmaMapper;
    }

    public NotaResponseDTO toResponseDTO(Nota nota) {
        if (nota == null) {
            return null;
        }

        MatriculaTurmaResponseDTO matriculaTurma = matriculaTurmaMapper.toResponseDTO(nota.getMatriculaTurma());

        return NotaResponseDTO.builder()
                .id(nota.getId())
                .matriculaTurma(matriculaTurma)
                .valor(nota.getValor())
                .peso(nota.getPeso())
                .descricao(nota.getDescricao())
                .dataAvaliacao(nota.getDataAvaliacao())
                .build();
    }
}
