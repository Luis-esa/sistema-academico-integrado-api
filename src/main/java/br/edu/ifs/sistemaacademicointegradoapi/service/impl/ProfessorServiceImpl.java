package br.edu.ifs.sistemaacademicointegradoapi.service.impl;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.ProfessorRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.ProfessorResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.mapper.ProfessorMapper;
import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.Professor;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.Usuario;
import br.edu.ifs.sistemaacademicointegradoapi.repository.ProfessorRepository;
import br.edu.ifs.sistemaacademicointegradoapi.service.ProfessorService;
import br.edu.ifs.sistemaacademicointegradoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;
    private final UsuarioService usuarioService;

    @Override
    public Professor buscarPorId(Long professorId) {
        return professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado."));
    }

    @Override
    public List<ProfessorResponseDTO> listarTodos(Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        return professorRepository.findAll().stream()
                .map(professorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProfessorResponseDTO criar(ProfessorRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Usuario usuario = usuarioService.buscarPorId(request.getUsuarioId());

        Professor professor = new Professor();
        professor.setUsuario(usuario);
        
        // A entidade Professor mapeia a matrícula como matriculaSiape, que é um registro diferente
        // da matrícula de Aluno. Portanto, recebemos o SIAPE do Request.
        professor.setMatriculaSiape(request.getMatriculaSiape());
        professor.setSuapId(request.getSuapId());
        professor.setStatus(StatusEnum.A);

        professor = professorRepository.save(professor);
        return professorMapper.toResponseDTO(professor);
    }

    @Override
    @Transactional
    public ProfessorResponseDTO editar(Long id, ProfessorRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Professor professor = buscarPorId(id);
        Usuario usuario = usuarioService.buscarPorId(request.getUsuarioId());

        professor.setUsuario(usuario);
        professor.setMatriculaSiape(request.getMatriculaSiape());
        professor.setSuapId(request.getSuapId());

        professor = professorRepository.save(professor);
        return professorMapper.toResponseDTO(professor);
    }

    @Override
    @Transactional
    public void inativar(Long id, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Professor professor = buscarPorId(id);
        professor.setStatus(StatusEnum.I);

        professorRepository.save(professor);
    }
}
