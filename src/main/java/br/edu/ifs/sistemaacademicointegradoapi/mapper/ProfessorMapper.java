package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.ProfessorResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public ProfessorResponseDTO toResponseDTO(Professor professor) {
        if (professor == null) {
            return null;
        }

        UsuarioResumoResponseDTO usuarioResumo = null;
        if (professor.getUsuario() != null) {
            usuarioResumo = UsuarioResumoResponseDTO.builder()
                    .id(professor.getUsuario().getId())
                    .nome(professor.getUsuario().getNome())
                    .email(professor.getUsuario().getEmail())
                    .login(professor.getUsuario().getLogin())
                    .build();
        }

        return ProfessorResponseDTO.builder()
                .id(professor.getId())
                .usuario(usuarioResumo)
                .matriculaSiape(professor.getMatriculaSiape())
                .statusEnum(professor.getStatus())
                .build();
    }
}
