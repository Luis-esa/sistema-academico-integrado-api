package br.edu.ifs.sistemaacademicointegradoapi.service;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.PeriodoLetivoRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.PeriodoLetivoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.PeriodoLetivo;

import java.util.List;

public interface PeriodoLetivoService {

    PeriodoLetivo buscarPorId(Long periodoLetivoId);

    List<PeriodoLetivoResponseDTO> listarTodos(Long usuarioIdAdministrador);

    PeriodoLetivoResponseDTO criar(PeriodoLetivoRequestDTO request, Long usuarioIdAdministrador);

    PeriodoLetivoResponseDTO editar(Long id, PeriodoLetivoRequestDTO request, Long usuarioIdAdministrador);

    void inativar(Long id, Long usuarioIdAdministrador);
}
