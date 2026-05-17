package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TurmaRequestDTO {

    @NotNull(message = "A disciplina deve ser informada!")
    private Long disciplinaId;

    @NotNull(message = "O professor deve ser informado!")
    private Long professorId;

    @NotNull(message = "O período letivo deve ser informado!")
    private Long periodoLetivoId;

    @NotBlank(message = "A descrição deve ser informada!")
    private String descricao;

    private String codigoSuap;
}
