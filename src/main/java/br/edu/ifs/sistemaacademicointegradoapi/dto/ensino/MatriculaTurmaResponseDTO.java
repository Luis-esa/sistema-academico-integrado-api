package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.model.SituacaoMatriculaEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class MatriculaTurmaResponseDTO {

    private Long id;
    private AlunoResponseDTO aluno;
    private TurmaResponseDTO turma;
    private SituacaoMatriculaEnum situacaoMatriculaEnum;
    private StatusEnum statusEnum;
    private LocalDate dataMatricula;

}
