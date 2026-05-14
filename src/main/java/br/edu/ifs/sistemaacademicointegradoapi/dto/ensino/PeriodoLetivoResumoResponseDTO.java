package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PeriodoLetivoResumoResponseDTO {

    private Long id;
    private Integer ano;
    private Integer semestre;
    private String descricao;

}
