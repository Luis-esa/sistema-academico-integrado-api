package br.edu.ifs.sistemaacademicointegradoapi.mapper;

import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.AlunoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.ensino.CursoResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.usuario.UsuarioResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    private final UsuarioMapper usuarioMapper;
    private final CursoMapper cursoMapper;

    public AlunoMapper(UsuarioMapper usuarioMapper, CursoMapper cursoMapper) {
        this.usuarioMapper = usuarioMapper;
        this.cursoMapper = cursoMapper;
    }

    public AlunoResponseDTO toResponseDTO(Aluno aluno) {
        if (aluno == null) {
            return null;
        }

        UsuarioResponseDTO usuario = usuarioMapper.toResponseDTO(aluno.getUsuario());
        CursoResponseDTO curso = cursoMapper.toResponseDTO(aluno.getCurso());

        return AlunoResponseDTO.builder()
                .id(aluno.getId())
                .usuario(usuario)
                .curso(curso)
                .matricula(aluno.getMatricula())
                .statusEnum(aluno.getStatus())
                // O AlunoResponseDTO possui dataCadastro, mas a entidade Aluno não o tem mapeado, 
                // então ficará null por padrão ou pode ser adicionado depois.
                .build();
    }
}
