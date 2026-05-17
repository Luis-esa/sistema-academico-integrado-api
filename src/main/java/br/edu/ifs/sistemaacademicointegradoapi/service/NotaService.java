package br.edu.ifs.sistemaacademicointegradoapi.service;

import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.NotaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.boletim.NotaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Nota;

import java.util.List;

public interface NotaService {

    Nota buscarPorId(Long notaId);

    List<NotaResponseDTO> listarTodos(Long usuarioIdAdministrador);

    NotaResponseDTO criar(NotaRequestDTO request, Long usuarioIdAdministrador);

    NotaResponseDTO editar(Long id, NotaRequestDTO request, Long usuarioIdAdministrador);

    // Nota não possui status, a assinatura é mantida para deleção
    void deletar(Long id, Long usuarioIdAdministrador);

    List<NotaResponseDTO> listarNotasDaMatricula(Long matriculaTurmaId);
}
