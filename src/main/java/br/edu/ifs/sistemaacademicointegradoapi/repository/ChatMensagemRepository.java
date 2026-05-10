package br.edu.ifs.sistemaacademicointegradoapi.repository;

import br.edu.ifs.sistemaacademicointegradoapi.model.ChatMensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMensagemRepository extends JpaRepository<ChatMensagem, Long> {

}
