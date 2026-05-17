package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProfessorRequestDTO {

    @NotNull(message = "O usuário deve ser informado!")
    private Long usuarioId;

    @NotBlank(message = "A matrícula SIAPE é obrigatória")
    private String matriculaSiape;

    private String suapId;

}
