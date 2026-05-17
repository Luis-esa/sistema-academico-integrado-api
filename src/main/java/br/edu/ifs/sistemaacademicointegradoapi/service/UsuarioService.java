package br.edu.ifs.sistemaacademicointegradoapi.service;

import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario buscarPorId(Long usuarioId);

    void validarPermissao(Long usuarioId, PerfilEnum... perfisPermitidos);

    List<UsuarioResponseDTO> listarTodos(Long usuarioIdAdministrador);

    UsuarioResponseDTO adicionar(UsuarioRequestDTO requestDTO);

    UsuarioResponseDTO editar(Long id, UsuarioRequestDTO requestDTO);

    void inativar(Long id, Long usuarioIdAdministrador);
}
