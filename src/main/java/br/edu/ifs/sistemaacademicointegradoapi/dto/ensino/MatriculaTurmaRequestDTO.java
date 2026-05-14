package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.model.SituacaoMatriculaEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatriculaTurmaRequestDTO {

    @NotNull(message = "O aluno deve ser informado!")
    private Long alunoId;

    @NotNull(message = "A turma deve ser informada!")
    private Long turmaId;

    private SituacaoMatriculaEnum situacaoMatriculaEnum;

}
