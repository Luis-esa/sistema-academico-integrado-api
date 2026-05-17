package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProfessorResponseDTO {

    private Long id;
    private UsuarioResumoResponseDTO usuario;
    private String matriculaSiape;
    private StatusEnum statusEnum;
    private String dataCadastro;

}
