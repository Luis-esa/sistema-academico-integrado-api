package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.AlunoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.TurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.MatriculaTurma;
import org.springframework.stereotype.Component;

@Component
public class MatriculaTurmaMapper {

    private final AlunoMapper alunoMapper;
    private final TurmaMapper turmaMapper;

    public MatriculaTurmaMapper(AlunoMapper alunoMapper, TurmaMapper turmaMapper) {
        this.alunoMapper = alunoMapper;
        this.turmaMapper = turmaMapper;
    }

    public MatriculaTurmaResponseDTO toResponseDTO(MatriculaTurma matriculaTurma) {
        if (matriculaTurma == null) {
            return null;
        }

        AlunoResponseDTO aluno = alunoMapper.toResponseDTO(matriculaTurma.getAluno());
        TurmaResponseDTO turma = turmaMapper.toResponseDTO(matriculaTurma.getTurma());

        return MatriculaTurmaResponseDTO.builder()
                .id(matriculaTurma.getId())
                .aluno(aluno)
                .turma(turma)
                .situacaoMatriculaEnum(matriculaTurma.getSituacaoMatriculaEnum())
                .statusEnum(matriculaTurma.getStatusEnum())
                .dataMatricula(matriculaTurma.getDataMatricula())
                .build();
    }
}
