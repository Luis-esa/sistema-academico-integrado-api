package br.edu.ifs.sistemaacademicointegradoapi.service.impl;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.mapper.MatriculaTurmaMapper;
import br.edu.ifs.sistemaacademicointegradoapi.model.Aluno;
import br.edu.ifs.sistemaacademicointegradoapi.model.MatriculaTurma;
import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.Turma;
import br.edu.ifs.sistemaacademicointegradoapi.repository.MatriculaTurmaRepository;
import br.edu.ifs.sistemaacademicointegradoapi.service.AlunoService;
import br.edu.ifs.sistemaacademicointegradoapi.service.MatriculaTurmaService;
import br.edu.ifs.sistemaacademicointegradoapi.service.TurmaService;
import br.edu.ifs.sistemaacademicointegradoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatriculaTurmaServiceImpl implements MatriculaTurmaService {

    private final MatriculaTurmaRepository matriculaTurmaRepository;
    private final MatriculaTurmaMapper matriculaTurmaMapper;

    private final AlunoService alunoService;
    private final TurmaService turmaService;
    private final UsuarioService usuarioService;

    @Override
    public MatriculaTurma buscarPorId(Long matriculaTurmaId) {
        return matriculaTurmaRepository.findById(matriculaTurmaId)
                .orElseThrow(() -> new RuntimeException("Matrícula de Turma não encontrada."));
    }

    @Override
    public List<MatriculaTurmaResponseDTO> listarTodos(Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        return matriculaTurmaRepository.findAll().stream()
                .map(matriculaTurmaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MatriculaTurmaResponseDTO criar(MatriculaTurmaRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Aluno aluno = alunoService.buscarPorId(request.getAlunoId());
        Turma turma = turmaService.buscarPorId(request.getTurmaId());

        MatriculaTurma matriculaTurma = new MatriculaTurma();
        matriculaTurma.setAluno(aluno);
        matriculaTurma.setTurma(turma);
        matriculaTurma.setSituacaoMatriculaEnum(request.getSituacaoMatriculaEnum());
        matriculaTurma.setDataMatricula(LocalDate.now());
        matriculaTurma.setStatusEnum(StatusEnum.A);

        matriculaTurma = matriculaTurmaRepository.save(matriculaTurma);
        return matriculaTurmaMapper.toResponseDTO(matriculaTurma);
    }

    @Override
    @Transactional
    public MatriculaTurmaResponseDTO editar(Long id, MatriculaTurmaRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        MatriculaTurma matriculaTurma = buscarPorId(id);

        Aluno aluno = alunoService.buscarPorId(request.getAlunoId());
        Turma turma = turmaService.buscarPorId(request.getTurmaId());

        matriculaTurma.setAluno(aluno);
        matriculaTurma.setTurma(turma);
        matriculaTurma.setSituacaoMatriculaEnum(request.getSituacaoMatriculaEnum());

        matriculaTurma = matriculaTurmaRepository.save(matriculaTurma);
        return matriculaTurmaMapper.toResponseDTO(matriculaTurma);
    }

    @Override
    @Transactional
    public void inativar(Long id, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        MatriculaTurma matriculaTurma = buscarPorId(id);
        matriculaTurma.setStatusEnum(StatusEnum.I);

        matriculaTurmaRepository.save(matriculaTurma);
    }

    @Override
    public List<MatriculaTurmaResponseDTO> listarAlunosPorTurma(Long turmaId) {
        return matriculaTurmaRepository.findByTurmaId(turmaId).stream()
                .map(matriculaTurmaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatriculaTurmaResponseDTO> listarDisciplinasDoAluno(Long alunoId) {
        return matriculaTurmaRepository.findByAlunoId(alunoId).stream()
                .map(matriculaTurmaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
