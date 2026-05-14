package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.WithBy;

@Builder
@Data
public class CursoResumoResponseDTO {

    private Long id;
    private String nome;

}
