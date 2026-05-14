package br.edu.ifs.sistemaacademicointegradoapi.dto.usuario;

import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuarioRequestDTO {
    @NotNull(message = "O ID do Administrador precisa ser informado!")
    private Long usuarioIdAdministrador;

    @NotBlank(message = "O nome precisa ser informado!")
    private String nome;

    @NotBlank(message = "O email precisa ser informado!")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "O login precisa ser informado!")
    private String login;

    @NotNull(message = "O perfil de usuário precisa ser informado!")
    private PerfilEnum perfilEnum;

    private String suapId;

    private String senha;

}
