package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AlunoResponseDTO {

    private Long id;
    private UsuarioResponseDTO usuario;
    private CursoResponseDTO curso;
    private String matricula;
    private StatusEnum statusEnum;
    private LocalDateTime dataCadastro;

}
