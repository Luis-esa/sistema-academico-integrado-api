package br.edu.ifs.sistemaacademicointegradoapi.service;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.ProfessorRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.ProfessorResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Professor;

import java.util.List;

public interface ProfessorService {

    Professor buscarPorId(Long professorId);

    List<ProfessorResponseDTO> listarTodos(Long usuarioIdAdministrador);

    ProfessorResponseDTO criar(ProfessorRequestDTO request, Long usuarioIdAdministrador);

    ProfessorResponseDTO editar(Long id, ProfessorRequestDTO request, Long usuarioIdAdministrador);

    void inativar(Long id, Long usuarioIdAdministrador);
}
