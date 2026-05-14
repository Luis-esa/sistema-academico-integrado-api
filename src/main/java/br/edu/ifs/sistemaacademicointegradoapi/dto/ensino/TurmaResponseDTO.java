package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class TurmaResponseDTO {

    private Long id;
    private DisciplinaResumoResponseDTO disciplina;
    private ProfessorResumoResponseDTO professor;
    private PeriodoLetivoResumoResponseDTO periodoLetivo;
    private String descricao;
    private String codigoSuap;
    private StatusEnum statusEnum;
    private LocalDateTime dataCadastro;

}
