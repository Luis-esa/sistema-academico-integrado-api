package br.edu.ifs.sistemaacademicointegradoapi.service;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.MatriculaTurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.MatriculaTurma;

import java.util.List;

public interface MatriculaTurmaService {

    MatriculaTurma buscarPorId(Long matriculaTurmaId);

    List<MatriculaTurmaResponseDTO> listarTodos(Long usuarioIdAdministrador);

    MatriculaTurmaResponseDTO criar(MatriculaTurmaRequestDTO request, Long usuarioIdAdministrador);

    MatriculaTurmaResponseDTO editar(Long id, MatriculaTurmaRequestDTO request, Long usuarioIdAdministrador);

    void inativar(Long id, Long usuarioIdAdministrador);

    List<MatriculaTurmaResponseDTO> listarAlunosPorTurma(Long turmaId);

    List<MatriculaTurmaResponseDTO> listarDisciplinasDoAluno(Long alunoId);
}
