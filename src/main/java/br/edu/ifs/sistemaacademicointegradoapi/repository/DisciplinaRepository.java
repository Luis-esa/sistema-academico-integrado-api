package br.edu.ifs.sistemaacademicointegradoapi.repository;

import br.edu.ifs.sistemaacademicointegradoapi.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

}
