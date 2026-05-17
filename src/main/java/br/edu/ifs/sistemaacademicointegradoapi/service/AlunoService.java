package br.edu.ifs.sistemaacademicointegradoapi.service;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.AlunoRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.AlunoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Aluno;

import java.util.List;

public interface AlunoService {

    Aluno buscarPorId(Long alunoId);

    List<AlunoResponseDTO> listarTodos(Long usuarioIdAdministrador);

    AlunoResponseDTO criar(AlunoRequestDTO request, Long usuarioIdAdministrador);

    AlunoResponseDTO editar(Long id, AlunoRequestDTO request, Long usuarioIdAdministrador);

    void inativar(Long id, Long usuarioIdAdministrador);
}
