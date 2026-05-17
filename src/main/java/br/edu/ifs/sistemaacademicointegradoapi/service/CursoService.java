package br.edu.ifs.sistemaacademicointegradoapi.service;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.CursoRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.CursoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Curso;

import java.util.List;

public interface CursoService {

    Curso buscarPorId(Long cursoId);

    List<CursoResponseDTO> listarTodos(Long usuarioIdAdministrador);

    CursoResponseDTO criar(CursoRequestDTO request, Long usuarioIdAdministrador);

    CursoResponseDTO editar(Long id, CursoRequestDTO request, Long usuarioIdAdministrador);

    void inativar(Long id, Long usuarioIdAdministrador);
}
