package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.DisciplinaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.PeriodoLetivoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.ProfessorResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.TurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Turma;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper {

    private final DisciplinaMapper disciplinaMapper;
    private final ProfessorMapper professorMapper;
    private final PeriodoLetivoMapper periodoLetivoMapper;

    public TurmaMapper(DisciplinaMapper disciplinaMapper, ProfessorMapper professorMapper, PeriodoLetivoMapper periodoLetivoMapper) {
        this.disciplinaMapper = disciplinaMapper;
        this.professorMapper = professorMapper;
        this.periodoLetivoMapper = periodoLetivoMapper;
    }

    public TurmaResponseDTO toResponseDTO(Turma turma) {
        if (turma == null) {
            return null;
        }

        DisciplinaResponseDTO disciplina = disciplinaMapper.toResponseDTO(turma.getDisciplina());
        ProfessorResponseDTO professor = professorMapper.toResponseDTO(turma.getProfessor());
        PeriodoLetivoResponseDTO periodoLetivo = periodoLetivoMapper.toResponseDTO(turma.getPeriodoLetivo());

        return TurmaResponseDTO.builder()
                .id(turma.getId())
                .disciplina(disciplina)
                .professor(professor)
                .periodoLetivo(periodoLetivo)
                .descricao(turma.getDescricao())
                .codigoSuap(turma.getCodigoSuap())
                .statusEnum(turma.getStatus())
                .build();
    }
}
