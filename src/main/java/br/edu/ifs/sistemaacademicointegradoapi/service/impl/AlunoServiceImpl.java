package br.edu.ifs.sistemaacademicointegradoapi.service.impl;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.AlunoRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.AlunoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.mapper.AlunoMapper;
import br.edu.ifs.sistemaacademicointegradoapi.model.Aluno;
import br.edu.ifs.sistemaacademicointegradoapi.model.Curso;
import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.Usuario;
import br.edu.ifs.sistemaacademicointegradoapi.repository.AlunoRepository;
import br.edu.ifs.sistemaacademicointegradoapi.repository.CursoRepository;
import br.edu.ifs.sistemaacademicointegradoapi.service.AlunoService;
import br.edu.ifs.sistemaacademicointegradoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioService usuarioService;
    private final AlunoMapper alunoMapper;

    @Override
    public Aluno buscarPorId(Long alunoId) {
        return alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
    }

    @Override
    public List<AlunoResponseDTO> listarTodos(Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        return alunoRepository.findAll().stream()
                .map(alunoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AlunoResponseDTO criar(AlunoRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Usuario usuario = usuarioService.buscarPorId(request.getUsuarioId());
        Curso curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado."));

        Aluno aluno = new Aluno();
        aluno.setUsuario(usuario);
        aluno.setCurso(curso);
        aluno.setSuapId(request.getCodigoSuap());
        aluno.setStatus(StatusEnum.A);

        
        aluno.setMatricula("TMP-" + UUID.randomUUID().toString().substring(0, 8));
        aluno = alunoRepository.save(aluno);

        int ano = LocalDate.now().getYear();
        String semestre = LocalDate.now().getMonthValue() <= 6 ? "01" : "02";
        String matriculaDefinitiva = ano + semestre + aluno.getId();

        aluno.setMatricula(matriculaDefinitiva);
        aluno = alunoRepository.save(aluno);

        return alunoMapper.toResponseDTO(aluno);
    }

    @Override
    @Transactional
    public AlunoResponseDTO editar(Long id, AlunoRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Aluno aluno = buscarPorId(id);

        Usuario usuario = usuarioService.buscarPorId(request.getUsuarioId());
        Curso curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado."));

        aluno.setUsuario(usuario);
        aluno.setCurso(curso);
        aluno.setSuapId(request.getCodigoSuap());

        aluno = alunoRepository.save(aluno);

        return alunoMapper.toResponseDTO(aluno);
    }

    @Override
    @Transactional
    public void inativar(Long id, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Aluno aluno = buscarPorId(id);
        aluno.setStatus(StatusEnum.I);

        alunoRepository.save(aluno);
    }
}
