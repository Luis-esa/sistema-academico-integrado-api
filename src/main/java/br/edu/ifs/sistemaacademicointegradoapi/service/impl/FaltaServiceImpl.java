package br.edu.ifs.sistemaacademicointegradoapi.service.impl;

import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.FaltaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.FaltaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.mapper.FaltaMapper;
import br.edu.ifs.sistemaacademicointegradoapi.model.Falta;
import br.edu.ifs.sistemaacademicointegradoapi.model.MatriculaTurma;
import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.repository.FaltaRepository;
import br.edu.ifs.sistemaacademicointegradoapi.service.FaltaService;
import br.edu.ifs.sistemaacademicointegradoapi.service.MatriculaTurmaService;
import br.edu.ifs.sistemaacademicointegradoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaltaServiceImpl implements FaltaService {

    private final FaltaRepository faltaRepository;
    private final FaltaMapper faltaMapper;
    private final MatriculaTurmaService matriculaTurmaService;
    private final UsuarioService usuarioService;

    @Override
    public Falta buscarPorId(Long faltaId) {
        return faltaRepository.findById(faltaId)
                .orElseThrow(() -> new RuntimeException("Falta não encontrada."));
    }

    @Override
    public List<FaltaResponseDTO> listarTodos(Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR, PerfilEnum.PROFESSOR);
        return faltaRepository.findAll().stream()
                .map(faltaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FaltaResponseDTO criar(FaltaRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR, PerfilEnum.PROFESSOR);

        MatriculaTurma matriculaTurma = matriculaTurmaService.buscarPorId(request.getMatriculaTurmaId());

        Falta falta = new Falta();
        falta.setMatriculaTurma(matriculaTurma);
        falta.setDataAula(request.getDataAula());
        falta.setQuantidadeFaltas(request.getQuantidadeFaltas() != null ? request.getQuantidadeFaltas() : 1);
        falta.setJustificativa(request.getJustificativa());
        falta.setCodigoSuap(request.getCodigoSuap());

        falta = faltaRepository.save(falta);
        return faltaMapper.toResponseDTO(falta);
    }

    @Override
    @Transactional
    public FaltaResponseDTO editar(Long id, FaltaRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR, PerfilEnum.PROFESSOR);

        Falta falta = buscarPorId(id);
        MatriculaTurma matriculaTurma = matriculaTurmaService.buscarPorId(request.getMatriculaTurmaId());

        falta.setMatriculaTurma(matriculaTurma);
        falta.setDataAula(request.getDataAula());
        falta.setQuantidadeFaltas(request.getQuantidadeFaltas() != null ? request.getQuantidadeFaltas() : 1);
        falta.setJustificativa(request.getJustificativa());
        falta.setCodigoSuap(request.getCodigoSuap());

        falta = faltaRepository.save(falta);
        return faltaMapper.toResponseDTO(falta);
    }

    @Override
    @Transactional
    public void deletar(Long id, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR, PerfilEnum.PROFESSOR);
        Falta falta = buscarPorId(id);
        faltaRepository.delete(falta);
    }

    @Override
    public List<FaltaResponseDTO> listarFaltasDaMatricula(Long matriculaTurmaId) {
        return faltaRepository.findByMatriculaTurmaId(matriculaTurmaId).stream()
                .map(faltaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
