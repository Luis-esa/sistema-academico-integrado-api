package br.edu.ifs.sistemaacademicointegradoapi.service.impl;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.TurmaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.TurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.mapper.TurmaMapper;
import br.edu.ifs.sistemaacademicointegradoapi.model.Disciplina;
import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.PeriodoLetivo;
import br.edu.ifs.sistemaacademicointegradoapi.model.Professor;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.Turma;
import br.edu.ifs.sistemaacademicointegradoapi.repository.TurmaRepository;
import br.edu.ifs.sistemaacademicointegradoapi.service.DisciplinaService;
import br.edu.ifs.sistemaacademicointegradoapi.service.PeriodoLetivoService;
import br.edu.ifs.sistemaacademicointegradoapi.service.ProfessorService;
import br.edu.ifs.sistemaacademicointegradoapi.service.TurmaService;
import br.edu.ifs.sistemaacademicointegradoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurmaServiceImpl implements TurmaService {

    private final TurmaRepository turmaRepository;
    private final TurmaMapper turmaMapper;
    
    private final UsuarioService usuarioService;
    private final DisciplinaService disciplinaService;
    private final ProfessorService professorService;
    private final PeriodoLetivoService periodoLetivoService;

    @Override
    public Turma buscarPorId(Long turmaId) {
        return turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada."));
    }

    @Override
    public List<TurmaResponseDTO> listarTodos(Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        return turmaRepository.findAll().stream()
                .map(turmaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TurmaResponseDTO criar(TurmaRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Disciplina disciplina = disciplinaService.buscarPorId(request.getDisciplinaId());
        Professor professor = professorService.buscarPorId(request.getProfessorId());
        PeriodoLetivo periodoLetivo = periodoLetivoService.buscarPorId(request.getPeriodoLetivoId());

        Turma turma = new Turma();
        turma.setDisciplina(disciplina);
        turma.setProfessor(professor);
        turma.setPeriodoLetivo(periodoLetivo);
        turma.setDescricao(request.getDescricao());
        turma.setCodigoSuap(request.getCodigoSuap());
        turma.setStatus(StatusEnum.A);

        turma = turmaRepository.save(turma);
        return turmaMapper.toResponseDTO(turma);
    }

    @Override
    @Transactional
    public TurmaResponseDTO editar(Long id, TurmaRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Turma turma = buscarPorId(id);
        
        Disciplina disciplina = disciplinaService.buscarPorId(request.getDisciplinaId());
        Professor professor = professorService.buscarPorId(request.getProfessorId());
        PeriodoLetivo periodoLetivo = periodoLetivoService.buscarPorId(request.getPeriodoLetivoId());

        turma.setDisciplina(disciplina);
        turma.setProfessor(professor);
        turma.setPeriodoLetivo(periodoLetivo);
        turma.setDescricao(request.getDescricao());
        turma.setCodigoSuap(request.getCodigoSuap());

        turma = turmaRepository.save(turma);
        return turmaMapper.toResponseDTO(turma);
    }

    @Override
    @Transactional
    public void inativar(Long id, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Turma turma = buscarPorId(id);
        turma.setStatus(StatusEnum.I);

        turmaRepository.save(turma);
    }

    @Override
    public List<TurmaResponseDTO> listarTurmasDoProfessor(Long professorId) {
        return turmaRepository.findByProfessorId(professorId).stream()
                .map(turmaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
