package br.edu.ifs.sistemaacademicointegradoapi.service;

import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.FaltaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.FaltaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Falta;

import java.util.List;

public interface FaltaService {

    Falta buscarPorId(Long faltaId);

    List<FaltaResponseDTO> listarTodos(Long usuarioIdAdministrador);

    FaltaResponseDTO criar(FaltaRequestDTO request, Long usuarioIdAdministrador);

    FaltaResponseDTO editar(Long id, FaltaRequestDTO request, Long usuarioIdAdministrador);

    void deletar(Long id, Long usuarioIdAdministrador);

    List<FaltaResponseDTO> listarFaltasDaMatricula(Long matriculaTurmaId);
}
