package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PeriodoLetivoRequestDTO {

    @NotNull(message = "O ano deve ser informado!")
    @Min(value = 2018, message = "O ano deve ser maior ou igual  a 2018")
    private Integer ano;

    @NotNull(message = "O semestre deve ser informado!")
    @Min(value = 1, message = "O semestre deve ser 1 ou 2")
    @Max(value = 2, message = "O semestre deve ser 1 ou 2")
    private Integer semestre;

    @NotNull(message = "A descrição precisa ser informada!")
    @Size(max = 50, message = "A descrição deve conter no máximo 50 caracteres")
    private String descricao;

    private StatusEnum statusEnum;

}
