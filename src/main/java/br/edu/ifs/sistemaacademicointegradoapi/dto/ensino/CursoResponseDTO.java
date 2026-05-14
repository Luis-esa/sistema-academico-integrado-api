package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CursoResponseDTO {

    private Long id;
    private String nome;
    private String codigoSuap;
    private StatusEnum statusEnum;
    private LocalDateTime dataCadastro;

}
