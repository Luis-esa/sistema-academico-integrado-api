package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AlunoResumoResponseDTO {

    private Long id;
    private String nome;
    private String matricula;

}
