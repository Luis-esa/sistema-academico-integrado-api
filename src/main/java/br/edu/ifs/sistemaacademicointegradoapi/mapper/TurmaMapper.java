package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.DisciplinaResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.PeriodoLetivoResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.ProfessorResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.TurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Turma;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper {

    public TurmaResponseDTO toResponseDTO(Turma turma) {
        if (turma == null) {
            return null;
        }

        DisciplinaResumoResponseDTO disciplinaResumo = null;
        if (turma.getDisciplina() != null) {
            disciplinaResumo = DisciplinaResumoResponseDTO.builder()
                    .id(turma.getDisciplina().getId())
                    .nome(turma.getDisciplina().getNome())
                    .codigo(turma.getDisciplina().getCodigo())
                    .build();
        }

        ProfessorResumoResponseDTO professorResumo = null;
        if (turma.getProfessor() != null) {
            professorResumo = ProfessorResumoResponseDTO.builder()
                    .id(turma.getProfessor().getId())
                    .nome(turma.getProfessor().getUsuario() != null ? turma.getProfessor().getUsuario().getNome() : null)
                    .matriculaSiape(turma.getProfessor().getMatriculaSiape())
                    .build();
        }

        PeriodoLetivoResumoResponseDTO periodoLetivoResumo = null;
        if (turma.getPeriodoLetivo() != null) {
            periodoLetivoResumo = PeriodoLetivoResumoResponseDTO.builder()
                    .id(turma.getPeriodoLetivo().getId())
                    .ano(turma.getPeriodoLetivo().getAno())
                    .semestre(turma.getPeriodoLetivo().getSemestre())
                    .descricao(turma.getPeriodoLetivo().getDescricao())
                    .build();
        }

        return TurmaResponseDTO.builder()
                .id(turma.getId())
                .disciplina(disciplinaResumo)
                .professor(professorResumo)
                .periodoLetivo(periodoLetivoResumo)
                .descricao(turma.getDescricao())
                .codigoSuap(turma.getCodigoSuap())
                .statusEnum(turma.getStatus())
                .build();
    }
}
