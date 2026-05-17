package br.edu.ifs.sistemaacademicointegradoapi.service;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.TurmaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.TurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Turma;

import java.util.List;

public interface TurmaService {

    Turma buscarPorId(Long turmaId);

    List<TurmaResponseDTO> listarTodos(Long usuarioIdAdministrador);

    TurmaResponseDTO criar(TurmaRequestDTO request, Long usuarioIdAdministrador);

    TurmaResponseDTO editar(Long id, TurmaRequestDTO request, Long usuarioIdAdministrador);

    void inativar(Long id, Long usuarioIdAdministrador);

    List<TurmaResponseDTO> listarTurmasDoProfessor(Long professorId);
}
