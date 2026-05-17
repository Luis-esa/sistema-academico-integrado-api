package br.edu.ifs.sistemaacademicointegradoapi.mapper;


import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .login(usuario.getLogin())
                .perfilEnum(usuario.getPerfil())
                .suapId(usuario.getSuapId())
                .statusEnum(usuario.getStatus())
                .dataCadastro(usuario.getDataCadastro())
                .build();
    }
}
