package br.edu.ifs.sistemaacademicointegradoapi.dto.boletim;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class FaltaResponseDTO {

    private Long id;
    private MatriculaTurmaResponseDTO matriculaTurma;
    private LocalDate dataAula;
    private Integer quantidadeFaltas;
    private String justificativa;
    private String codigoSuap;

}
