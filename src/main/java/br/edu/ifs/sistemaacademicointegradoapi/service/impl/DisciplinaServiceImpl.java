package br.edu.ifs.sistemaacademicointegradoapi.service.impl;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.DisciplinaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.DisciplinaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.mapper.DisciplinaMapper;
import br.edu.ifs.sistemaacademicointegradoapi.model.Disciplina;
import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import br.edu.ifs.sistemaacademicointegradoapi.repository.DisciplinaRepository;
import br.edu.ifs.sistemaacademicointegradoapi.service.DisciplinaService;
import br.edu.ifs.sistemaacademicointegradoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final DisciplinaMapper disciplinaMapper;
    private final UsuarioService usuarioService;

    @Override
    public Disciplina buscarPorId(Long disciplinaId) {
        return disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada."));
    }

    @Override
    public List<DisciplinaResponseDTO> listarTodos(Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        return disciplinaRepository.findAll().stream()
                .map(disciplinaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DisciplinaResponseDTO criar(DisciplinaRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(request.getNome());
        disciplina.setCodigo(request.getCodigo());
        disciplina.setCodigoSuap(request.getCodigoSuap());
        disciplina.setCargaHoraria(request.getCargaHoraria());
        disciplina.setStatus(request.getStatusEnum() != null ? request.getStatusEnum() : StatusEnum.A);

        disciplina = disciplinaRepository.save(disciplina);
        return disciplinaMapper.toResponseDTO(disciplina);
    }

    @Override
    @Transactional
    public DisciplinaResponseDTO editar(Long id, DisciplinaRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Disciplina disciplina = buscarPorId(id);
        disciplina.setNome(request.getNome());
        disciplina.setCodigo(request.getCodigo());
        disciplina.setCodigoSuap(request.getCodigoSuap());
        disciplina.setCargaHoraria(request.getCargaHoraria());
        
        if (request.getStatusEnum() != null) {
            disciplina.setStatus(request.getStatusEnum());
        }

        disciplina = disciplinaRepository.save(disciplina);
        return disciplinaMapper.toResponseDTO(disciplina);
    }

    @Override
    @Transactional
    public void inativar(Long id, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Disciplina disciplina = buscarPorId(id);
        disciplina.setStatus(StatusEnum.I);

        disciplinaRepository.save(disciplina);
    }
}
