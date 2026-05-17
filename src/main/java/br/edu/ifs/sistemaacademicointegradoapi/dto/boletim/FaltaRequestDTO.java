package br.edu.ifs.sistemaacademicointegradoapi.dto.boletim;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FaltaRequestDTO {

    @NotNull(message = "A Matrícula da Turma deve ser informada!")
    private Long matriculaTurmaId;

    @NotNull(message = "A data da aula deve ser informada!")
    private LocalDate dataAula;

    @NotNull(message = "A quantidade de faltas deve ser informada!")
    @Min(value = 1, message = "A quantidade de faltas deve ser maior que zero")
    private Integer quantidadeFaltas;

    private String justificativa;

    private String codigoSuap;
}
