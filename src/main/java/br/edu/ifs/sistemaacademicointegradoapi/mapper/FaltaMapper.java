package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.FaltaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Falta;
import org.springframework.stereotype.Component;

@Component
public class FaltaMapper {

    public FaltaResponseDTO toResponseDTO(Falta falta) {
        if (falta == null) {
            return null;
        }

        MatriculaTurmaResumoResponseDTO matriculaResumo = null;
        if (falta.getMatriculaTurma() != null) {
            matriculaResumo = MatriculaTurmaResumoResponseDTO.builder()
                    .id(falta.getMatriculaTurma().getId())
                    .alunoNome(falta.getMatriculaTurma().getAluno() != null && falta.getMatriculaTurma().getAluno().getUsuario() != null ? falta.getMatriculaTurma().getAluno().getUsuario().getNome() : null)
                    .alunoMatricula(falta.getMatriculaTurma().getAluno() != null ? falta.getMatriculaTurma().getAluno().getMatricula() : null)
                    .turmaDescricao(falta.getMatriculaTurma().getTurma() != null ? falta.getMatriculaTurma().getTurma().getDescricao() : null)
                    .situacaoMatriculaEnum(falta.getMatriculaTurma().getSituacaoMatriculaEnum())
                    .build();
        }

        return FaltaResponseDTO.builder()
                .id(falta.getId())
                .matriculaTurma(matriculaResumo)
                .dataAula(falta.getDataAula())
                .quantidadeFaltas(falta.getQuantidadeFaltas())
                .justificativa(falta.getJustificativa())
                .codigoSuap(falta.getCodigoSuap())
                .build();
    }
}
