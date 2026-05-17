package br.edu.ifs.sistemaacademicointegradoapi.dto.ensino;

import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AlunoResponseDTO {

    private Long id;
    private UsuarioResumoResponseDTO usuario;
    private CursoResumoResponseDTO curso;
    private String matricula;
    private StatusEnum statusEnum;
    private LocalDateTime dataCadastro;

}
