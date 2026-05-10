package br.edu.ifs.sistemaacademicointegradoapi.repository;

import br.edu.ifs.sistemaacademicointegradoapi.model.ChatTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatTurmaRepository extends JpaRepository<ChatTurma, Long> {

}
