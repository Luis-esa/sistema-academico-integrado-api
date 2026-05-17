package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class PeriodoLetivoResponseDTO {

    private Long id;
    private Integer ano;
    private Integer semestre;
    private String descricao;
    private StatusEnum statusEnum;
    private LocalDateTime dataCadastro;

}
