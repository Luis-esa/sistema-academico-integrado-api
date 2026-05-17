package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlunoRequestDTO {

    @NotNull(message = "O usuário deve ser informado!")
    private Long usuarioId;

    @NotNull(message = "O curso deve ser informado!")
    private Long cursoId;

    private String codigoSuap;

}
