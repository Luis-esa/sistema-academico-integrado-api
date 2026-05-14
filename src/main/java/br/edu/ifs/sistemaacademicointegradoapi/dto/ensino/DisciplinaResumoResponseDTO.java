package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DisciplinaResumoResponseDTO {

    private Long id;
    private String nome;
    private String codigo;

}
