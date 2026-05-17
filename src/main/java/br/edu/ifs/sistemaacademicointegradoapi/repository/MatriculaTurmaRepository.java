package br.edu.ifs.sistemaacademicointegradoapi.repository;

import br.edu.ifs.sistemaacademicointegradoapi.model.MatriculaTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaTurmaRepository extends JpaRepository<MatriculaTurma, Long> {

    List<MatriculaTurma> findByTurmaId(Long turmaId);

    List<MatriculaTurma> findByAlunoId(Long alunoId);

}
