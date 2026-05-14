package br.edu.ifs.sistemaacademicointegradoapi.dto.boletim;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class NotaResponseDTO {

    private Long id;
    private MatriculaTurmaResumoResponseDTO matriculaTurma;
    private BigDecimal valor;
    private BigDecimal peso;
    private String descricao;
    private StatusEnum statusEnum;
    private LocalDate dataAvaliacao;
    private LocalDateTime dataCriacao;
}
