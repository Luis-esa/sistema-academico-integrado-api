package br.edu.ifs.sistemaacademicointegradoapi.dto.usuario;

import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private PerfilEnum perfilEnum;
    private String suapId;
    private StatusEnum statusEnum;
    private LocalDateTime dataCadastro;

}
