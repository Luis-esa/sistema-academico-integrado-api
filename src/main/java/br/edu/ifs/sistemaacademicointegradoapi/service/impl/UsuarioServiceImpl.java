package br.edu.ifs.sistemaacademicointegradoapi.service.impl;

import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.mapper.UsuarioMapper;
import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.Usuario;
import br.edu.ifs.sistemaacademicointegradoapi.repository.UsuarioRepository;
import br.edu.ifs.sistemaacademicointegradoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Usuario buscarPorId(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    @Override
    public void validarPermissao(Long usuarioId, PerfilEnum... perfisPermitidos) {
        Usuario usuario = buscarPorId(usuarioId);
        List<PerfilEnum> permitidos = Arrays.asList(perfisPermitidos);

        if (!permitidos.contains(usuario.getPerfil())) {
            throw new RuntimeException("Acesso negado!");
        }
    }

    @Override
    public List<UsuarioResponseDTO> listarTodos(Long usuarioIdAdministrador) {
        validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        return usuarioRepository.findAll().stream().map(usuarioMapper::toResponseDTO).toList();
    }

    @Override
    public UsuarioResponseDTO adicionar(UsuarioRequestDTO requestDTO) {
        validarPermissao(requestDTO.getUsuarioIdAdministrador(), PerfilEnum.COORDENADOR);

        Usuario usuario = new Usuario();
        usuario.setNome(requestDTO.getNome());
        usuario.setEmail(requestDTO.getEmail());
        usuario.setLogin(requestDTO.getLogin());
        usuario.setPerfil(requestDTO.getPerfilEnum());
        usuario.setSuapId(requestDTO.getSuapId());
        usuario.setSenha(requestDTO.getSenha());
        usuario.setStatus(StatusEnum.A);

        Usuario usuarioCriado = usuarioRepository.save(usuario);

        return usuarioMapper.toResponseDTO(usuarioCriado);
    }

    @Override
    public UsuarioResponseDTO editar (Long id, UsuarioRequestDTO requestDTO) {
        validarPermissao(requestDTO.getUsuarioIdAdministrador(), PerfilEnum.COORDENADOR);

        Usuario usuario = buscarPorId(id);
        usuario.setNome(requestDTO.getNome());
        usuario.setEmail(requestDTO.getEmail());
        usuario.setLogin(requestDTO.getLogin());
        usuario.setPerfil(requestDTO.getPerfilEnum());
        usuario.setSuapId(requestDTO.getSuapId());

        if (requestDTO.getSenha() != null && !requestDTO.getSenha().trim().isEmpty()) {
            usuario.setSenha(requestDTO.getSenha());
        }

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return usuarioMapper.toResponseDTO(usuarioAtualizado);
    }

    @Override
    public void inativar(Long id, Long usuarioIdAdministrador) {
        validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Usuario usuario = buscarPorId(id);
        usuario.setStatus(StatusEnum.I);

        usuarioRepository.save(usuario);
    }
}
