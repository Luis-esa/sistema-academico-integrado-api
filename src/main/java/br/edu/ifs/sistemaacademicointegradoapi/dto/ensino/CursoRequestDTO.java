package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CursoRequestDTO {

    @NotBlank(message = "O nome do curso precisa ser informado!")
    private String nome;

    private String codigoSuap;

    private StatusEnum statusEnum;

}
