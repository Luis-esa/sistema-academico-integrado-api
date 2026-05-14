package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.model.SituacaoMatriculaEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class MatriculaTurmaResponseDTO {

    private Long id;
    private AlunoResumoResponseDTO aluno;
    private TurmaResumoResponseDTO turma;
    private SituacaoMatriculaEnum situacaoMatriculaEnum;
    private StatusEnum statusEnum;
    private LocalDateTime dataCadastro;

}
