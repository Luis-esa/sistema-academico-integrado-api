package br.edu.ifs.sistemaacademicointegradoapi.service.impl;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.PeriodoLetivoRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.PeriodoLetivoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.mapper.PeriodoLetivoMapper;
import br.edu.ifs.sistemaacademicointegradoapi.model.PeriodoLetivo;
import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import br.edu.ifs.sistemaacademicointegradoapi.repository.PeriodoLetivoRepository;
import br.edu.ifs.sistemaacademicointegradoapi.service.PeriodoLetivoService;
import br.edu.ifs.sistemaacademicointegradoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeriodoLetivoServiceImpl implements PeriodoLetivoService {

    private final PeriodoLetivoRepository periodoLetivoRepository;
    private final PeriodoLetivoMapper periodoLetivoMapper;
    private final UsuarioService usuarioService;

    @Override
    public PeriodoLetivo buscarPorId(Long periodoLetivoId) {
        return periodoLetivoRepository.findById(periodoLetivoId)
                .orElseThrow(() -> new RuntimeException("Período Letivo não encontrado."));
    }

    @Override
    public List<PeriodoLetivoResponseDTO> listarTodos(Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        return periodoLetivoRepository.findAll().stream()
                .map(periodoLetivoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PeriodoLetivoResponseDTO criar(PeriodoLetivoRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        PeriodoLetivo periodoLetivo = new PeriodoLetivo();
        periodoLetivo.setAno(request.getAno());
        periodoLetivo.setSemestre(request.getSemestre());
        periodoLetivo.setDescricao(request.getDescricao());
        periodoLetivo.setStatus(request.getStatusEnum() != null ? request.getStatusEnum() : StatusEnum.A);

        periodoLetivo = periodoLetivoRepository.save(periodoLetivo);
        return periodoLetivoMapper.toResponseDTO(periodoLetivo);
    }

    @Override
    @Transactional
    public PeriodoLetivoResponseDTO editar(Long id, PeriodoLetivoRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        PeriodoLetivo periodoLetivo = buscarPorId(id);
        periodoLetivo.setAno(request.getAno());
        periodoLetivo.setSemestre(request.getSemestre());
        periodoLetivo.setDescricao(request.getDescricao());
        
        if (request.getStatusEnum() != null) {
            periodoLetivo.setStatus(request.getStatusEnum());
        }

        periodoLetivo = periodoLetivoRepository.save(periodoLetivo);
        return periodoLetivoMapper.toResponseDTO(periodoLetivo);
    }

    @Override
    @Transactional
    public void inativar(Long id, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        PeriodoLetivo periodoLetivo = buscarPorId(id);
        periodoLetivo.setStatus(StatusEnum.I);

        periodoLetivoRepository.save(periodoLetivo);
    }
}
