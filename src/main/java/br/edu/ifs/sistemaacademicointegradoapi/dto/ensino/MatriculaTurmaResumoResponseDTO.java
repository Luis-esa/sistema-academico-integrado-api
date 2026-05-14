package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.model.SituacaoMatriculaEnum;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MatriculaTurmaResumoResponseDTO {

    private Long id;
    private String alunoNome;
    private String alunoMatricula;
    private String turmaDescricao;
    private SituacaoMatriculaEnum situacaoMatriculaEnum;
}
