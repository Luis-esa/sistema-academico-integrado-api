package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProfessorResponseDTO {

    private Long id;
    private UsuarioResponseDTO usuario;
    private String matriculaSiape;
    private StatusEnum statusEnum;
    private String dataCadastro;

}
