package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.AlunoResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.TurmaResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.MatriculaTurma;
import org.springframework.stereotype.Component;

@Component
public class MatriculaTurmaMapper {

    public MatriculaTurmaResponseDTO toResponseDTO(MatriculaTurma matriculaTurma) {
        if (matriculaTurma == null) {
            return null;
        }

        AlunoResumoResponseDTO alunoResumo = null;
        if (matriculaTurma.getAluno() != null) {
            alunoResumo = AlunoResumoResponseDTO.builder()
                    .id(matriculaTurma.getAluno().getId())
                    .nome(matriculaTurma.getAluno().getUsuario() != null ? matriculaTurma.getAluno().getUsuario().getNome() : null)
                    .matricula(matriculaTurma.getAluno().getMatricula())
                    .build();
        }

        TurmaResumoResponseDTO turmaResumo = null;
        if (matriculaTurma.getTurma() != null) {
            turmaResumo = TurmaResumoResponseDTO.builder()
                    .id(matriculaTurma.getTurma().getId())
                    .descricao(matriculaTurma.getTurma().getDescricao())
                    .disciplinaNome(matriculaTurma.getTurma().getDisciplina() != null ? matriculaTurma.getTurma().getDisciplina().getNome() : null)
                    .professorNome(matriculaTurma.getTurma().getProfessor() != null && matriculaTurma.getTurma().getProfessor().getUsuario() != null ? matriculaTurma.getTurma().getProfessor().getUsuario().getNome() : null)
                    .build();
        }

        return MatriculaTurmaResponseDTO.builder()
                .id(matriculaTurma.getId())
                .aluno(alunoResumo)
                .turma(turmaResumo)
                .situacaoMatriculaEnum(matriculaTurma.getSituacaoMatriculaEnum())
                .statusEnum(matriculaTurma.getStatusEnum())
                .build();
    }
}
