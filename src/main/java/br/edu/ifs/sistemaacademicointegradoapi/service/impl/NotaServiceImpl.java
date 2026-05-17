package br.edu.ifs.sistemaacademicointegradoapi.service.impl;

import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.NotaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.NotaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.mapper.NotaMapper;
import br.edu.ifs.sistemaacademicointegradoapi.model.MatriculaTurma;
import br.edu.ifs.sistemaacademicointegradoapi.model.Nota;
import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.repository.NotaRepository;
import br.edu.ifs.sistemaacademicointegradoapi.service.MatriculaTurmaService;
import br.edu.ifs.sistemaacademicointegradoapi.service.NotaService;
import br.edu.ifs.sistemaacademicointegradoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {

    private final NotaRepository notaRepository;
    private final NotaMapper notaMapper;

    private final MatriculaTurmaService matriculaTurmaService;
    private final UsuarioService usuarioService;

    @Override
    public Nota buscarPorId(Long notaId) {
        return notaRepository.findById(notaId)
                .orElseThrow(() -> new RuntimeException("Nota não encontrada."));
    }

    @Override
    public List<NotaResponseDTO> listarTodos(Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR, PerfilEnum.PROFESSOR);

        return notaRepository.findAll().stream()
                .map(notaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NotaResponseDTO criar(NotaRequestDTO request, Long usuarioIdAdministrador) {
        // Professor ou Coordenador podem lançar nota
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR, PerfilEnum.PROFESSOR);

        MatriculaTurma matriculaTurma = matriculaTurmaService.buscarPorId(request.getMatriculaTurmaId());

        Nota nota = new Nota();
        nota.setMatriculaTurma(matriculaTurma);
        nota.setDescricao(request.getDescricao());
        nota.setValor(request.getValor());
        nota.setPeso(request.getPeso());
        nota.setDataAvaliacao(request.getDataAvaliacao());

        nota = notaRepository.save(nota);
        return notaMapper.toResponseDTO(nota);
    }

    @Override
    @Transactional
    public NotaResponseDTO editar(Long id, NotaRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR, PerfilEnum.PROFESSOR);

        Nota nota = buscarPorId(id);
        MatriculaTurma matriculaTurma = matriculaTurmaService.buscarPorId(request.getMatriculaTurmaId());

        nota.setMatriculaTurma(matriculaTurma);
        nota.setDescricao(request.getDescricao());
        nota.setValor(request.getValor());
        nota.setPeso(request.getPeso());
        nota.setDataAvaliacao(request.getDataAvaliacao());

        nota = notaRepository.save(nota);
        return notaMapper.toResponseDTO(nota);
    }

    @Override
    @Transactional
    public void deletar(Long id, Long usuarioIdAdministrador) {
        // Como Nota não possui status para inativação, deletamos fisicamente.
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR, PerfilEnum.PROFESSOR);

        Nota nota = buscarPorId(id);
        notaRepository.delete(nota);
    }

    @Override
    public List<NotaResponseDTO> listarNotasDaMatricula(Long matriculaTurmaId) {
        return notaRepository.findByMatriculaTurmaId(matriculaTurmaId).stream()
                .map(notaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
