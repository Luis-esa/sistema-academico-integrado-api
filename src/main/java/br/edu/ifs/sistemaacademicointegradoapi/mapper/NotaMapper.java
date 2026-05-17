package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.NotaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    public NotaResponseDTO toResponseDTO(Nota nota) {
        if (nota == null) {
            return null;
        }

        MatriculaTurmaResumoResponseDTO matriculaResumo = null;
        if (nota.getMatriculaTurma() != null) {
            matriculaResumo = MatriculaTurmaResumoResponseDTO.builder()
                    .id(nota.getMatriculaTurma().getId())
                    .alunoNome(nota.getMatriculaTurma().getAluno() != null && nota.getMatriculaTurma().getAluno().getUsuario() != null ? nota.getMatriculaTurma().getAluno().getUsuario().getNome() : null)
                    .alunoMatricula(nota.getMatriculaTurma().getAluno() != null ? nota.getMatriculaTurma().getAluno().getMatricula() : null)
                    .turmaDescricao(nota.getMatriculaTurma().getTurma() != null ? nota.getMatriculaTurma().getTurma().getDescricao() : null)
                    .situacaoMatriculaEnum(nota.getMatriculaTurma().getSituacaoMatriculaEnum())
                    .build();
        }

        return NotaResponseDTO.builder()
                .id(nota.getId())
                .matriculaTurma(matriculaResumo)
                .valor(nota.getValor())
                .peso(nota.getPeso())
                .descricao(nota.getDescricao())
                .dataAvaliacao(nota.getDataAvaliacao())
                // O NotaResponseDTO possui dataCriacao e statusEnum, porém a entidade Nota não os possui.
                .build();
    }
}
