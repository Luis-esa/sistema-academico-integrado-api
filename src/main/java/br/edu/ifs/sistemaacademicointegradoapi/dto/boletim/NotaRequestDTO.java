package br.edu.ifs.sistemaacademicointegradoapi.dto.boletim;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class NotaRequestDTO {

    @NotNull(message = "Matrícula na turma deve ser informada!")
    private Long matriculaTurmaId;

    @NotBlank(message = "A Descrição deve ser informada!")
    @Size(max = 100, message = "A descrição deve conter no máximo 100 caracteres")
    private String descricao;

    @NotNull(message = "O valor da nota deve ser informado!")
    @DecimalMin(value = "0.00", inclusive = false, message = "O valor da nota deve ser maior que zero")
    @DecimalMax(value = "10.00", inclusive = false, message = "O valor da nota deve ser no máximo 10")
    private BigDecimal valor;

    @NotNull(message = "O peso da nota deve ser informado!")
    @DecimalMin(value = "0.00", inclusive = false, message = "O peso da nota deve ser maior que zero")
    @DecimalMax(value = "1", message = "O peso deve ser no máximo 1.0")
    private BigDecimal peso;

    private LocalDate dataAvaliacao;

    private LocalDateTime dataCriacao;

}
