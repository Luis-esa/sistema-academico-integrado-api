package br.edu.ifs.sistemaacademicointegradoapi.service;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.DisciplinaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.DisciplinaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Disciplina;

import java.util.List;

public interface DisciplinaService {

    Disciplina buscarPorId(Long disciplinaId);

    List<DisciplinaResponseDTO> listarTodos(Long usuarioIdAdministrador);

    DisciplinaResponseDTO criar(DisciplinaRequestDTO request, Long usuarioIdAdministrador);

    DisciplinaResponseDTO editar(Long id, DisciplinaRequestDTO request, Long usuarioIdAdministrador);

    void inativar(Long id, Long usuarioIdAdministrador);
}
