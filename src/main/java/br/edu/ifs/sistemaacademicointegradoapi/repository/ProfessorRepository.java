package br.edu.ifs.sistemaacademicointegradoapi.repository;

import br.edu.ifs.sistemaacademicointegradoapi.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
