package br.edu.ifs.sistemaacademicointegradoapi.repository;

import br.edu.ifs.sistemaacademicointegradoapi.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

}
