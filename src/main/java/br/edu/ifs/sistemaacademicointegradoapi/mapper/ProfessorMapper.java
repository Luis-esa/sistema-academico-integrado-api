package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.ProfessorResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    private final UsuarioMapper usuarioMapper;

    public ProfessorMapper(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    public ProfessorResponseDTO toResponseDTO(Professor professor) {
        if (professor == null) {
            return null;
        }

        UsuarioResponseDTO usuario = usuarioMapper.toResponseDTO(professor.getUsuario());

        return ProfessorResponseDTO.builder()
                .id(professor.getId())
                .usuario(usuario)
                .matriculaSiape(professor.getMatriculaSiape())
                .statusEnum(professor.getStatus())
                .build();
    }
}
