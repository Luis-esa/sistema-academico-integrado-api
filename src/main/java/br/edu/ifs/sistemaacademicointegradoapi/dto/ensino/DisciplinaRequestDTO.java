package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DisciplinaRequestDTO {

    @NotBlank(message = "O nome da disciplina precisa ser informado!")
    private String nome;

    @NotBlank(message = "O código precisa ser informado!")
    private String codigo;

    private String codigoSuap;

    @NotNull(message = "A carga horária deve ser informada!")
    @Min(value = 1, message = "Carga horária deve ser maior que zero")
    private Integer cargaHoraria;

    private StatusEnum statusEnum;
}
