package br.edu.ifs.sistemaacademicointegradoapi.service.impl;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.CursoRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.CursoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.mapper.CursoMapper;
import br.edu.ifs.sistemaacademicointegradoapi.model.Curso;
import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import br.edu.ifs.sistemaacademicointegradoapi.repository.CursoRepository;
import br.edu.ifs.sistemaacademicointegradoapi.service.CursoService;
import br.edu.ifs.sistemaacademicointegradoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;
    private final UsuarioService usuarioService;

    @Override
    public Curso buscarPorId(Long cursoId) {
        return cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado."));
    }

    @Override
    public List<CursoResponseDTO> listarTodos(Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        return cursoRepository.findAll().stream()
                .map(cursoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CursoResponseDTO criar(CursoRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Curso curso = new Curso();
        curso.setNome(request.getNome());
        curso.setCodigoSuap(request.getCodigoSuap());
        curso.setStatus(request.getStatusEnum() != null ? request.getStatusEnum() : StatusEnum.A);

        curso = cursoRepository.save(curso);
        return cursoMapper.toResponseDTO(curso);
    }

    @Override
    @Transactional
    public CursoResponseDTO editar(Long id, CursoRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Curso curso = buscarPorId(id);
        curso.setNome(request.getNome());
        curso.setCodigoSuap(request.getCodigoSuap());
        
        if (request.getStatusEnum() != null) {
            curso.setStatus(request.getStatusEnum());
        }

        curso = cursoRepository.save(curso);
        return cursoMapper.toResponseDTO(curso);
    }

    @Override
    @Transactional
    public void inativar(Long id, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Curso curso = buscarPorId(id);
        curso.setStatus(StatusEnum.I);

        cursoRepository.save(curso);
    }
}
