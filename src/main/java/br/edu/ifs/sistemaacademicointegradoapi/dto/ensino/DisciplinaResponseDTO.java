package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class DisciplinaResponseDTO {

    private Long id;
    private String nome;
    private String codigo;
    private String codigoSuap;
    private Integer cargaHoraria;
    private StatusEnum statusEnum;
    private LocalDateTime dataCadastro;

}
