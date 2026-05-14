package br.edu.ifs.sistemaacademicointegradoapi.dto.usuario;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioResumoResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String login;

}
