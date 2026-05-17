package br.edu.ifs.sistemaacademicointegradoapi.service.impl;

import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatMensagemRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatMensagemResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.mapper.ChatMensagemMapper;
import br.edu.ifs.sistemaacademicointegradoapi.model.ChatMensagem;
import br.edu.ifs.sistemaacademicointegradoapi.model.ChatTurma;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.Usuario;
import br.edu.ifs.sistemaacademicointegradoapi.repository.ChatMensagemRepository;
import br.edu.ifs.sistemaacademicointegradoapi.service.ChatMensagemService;
import br.edu.ifs.sistemaacademicointegradoapi.service.ChatTurmaService;
import br.edu.ifs.sistemaacademicointegradoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMensagemServiceImpl implements ChatMensagemService {

    private final ChatMensagemRepository chatMensagemRepository;
    private final ChatMensagemMapper chatMensagemMapper;
    private final ChatTurmaService chatTurmaService;
    private final UsuarioService usuarioService;

    @Override
    public List<ChatMensagemResponseDTO> listarMensagensPorChat(Long chatTurmaId, Long usuarioIdReq) {
        // Futuramente: Adicionar validação se o usuário faz parte da turma (Aluno ou Professor)
        Usuario usuario = usuarioService.buscarPorId(usuarioIdReq);
        
        return chatMensagemRepository.findAll().stream()
                .filter(m -> m.getChatTurma().getId().equals(chatTurmaId))
                .map(chatMensagemMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ChatMensagemResponseDTO enviarMensagem(ChatMensagemRequestDTO request, Long usuarioIdReq) {
        // Futuramente: Adicionar validação se o usuário faz parte da turma
        Usuario usuario = usuarioService.buscarPorId(usuarioIdReq);
        ChatTurma chatTurma = chatTurmaService.buscarPorId(request.getChatTurmaId());

        ChatMensagem chatMensagem = new ChatMensagem();
        chatMensagem.setChatTurma(chatTurma);
        chatMensagem.setUsuario(usuario);
        chatMensagem.setMensagem(request.getMensagem());
        chatMensagem.setStatusEnum(StatusEnum.A);
        // O campo dataDeEnvio é gerado automaticamente pelo banco (insertable = false)
        
        chatMensagem = chatMensagemRepository.save(chatMensagem);
        return chatMensagemMapper.toResponseDTO(chatMensagem);
    }

    @Override
    @Transactional
    public void inativarMensagem(Long id, Long usuarioIdReq) {
        ChatMensagem chatMensagem = chatMensagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada."));
                
        // Regra de Negócio: Apenas o dono da mensagem pode inativá-la (ou poderíamos permitir a um Admin)
        if (!chatMensagem.getUsuario().getId().equals(usuarioIdReq)) {
             throw new RuntimeException("Você não tem permissão para inativar esta mensagem. Apenas o autor pode apagá-la.");
        }

        chatMensagem.setStatusEnum(StatusEnum.I);
        chatMensagemRepository.save(chatMensagem);
    }
}
