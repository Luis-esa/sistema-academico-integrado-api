package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TurmaResumoResponseDTO {

    private Long id;
    private String descricao;
    private String disciplinaNome;
    private String professorNome;

}
