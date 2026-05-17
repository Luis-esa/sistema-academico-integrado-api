package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.AlunoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.CursoResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResumoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public AlunoResponseDTO toResponseDTO(Aluno aluno) {
        if (aluno == null) {
            return null;
        }

        UsuarioResumoResponseDTO usuarioResumo = null;
        if (aluno.getUsuario() != null) {
            usuarioResumo = UsuarioResumoResponseDTO.builder()
                    .id(aluno.getUsuario().getId())
                    .nome(aluno.getUsuario().getNome())
                    .email(aluno.getUsuario().getEmail())
                    .login(aluno.getUsuario().getLogin())
                    .build();
        }

        CursoResumoResponseDTO cursoResumo = null;
        if (aluno.getCurso() != null) {
            cursoResumo = CursoResumoResponseDTO.builder()
                    .id(aluno.getCurso().getId())
                    .nome(aluno.getCurso().getNome())
                    .build();
        }

        return AlunoResponseDTO.builder()
                .id(aluno.getId())
                .usuario(usuarioResumo)
                .curso(cursoResumo)
                .matricula(aluno.getMatricula())
                .statusEnum(aluno.getStatus())
                // O AlunoResponseDTO possui dataCadastro, mas a entidade Aluno não o tem mapeado, 
                // então ficará null por padrão ou pode ser adicionado depois.
                .build();
    }
}
