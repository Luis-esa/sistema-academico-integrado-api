package br.edu.ifs.sistemaacademicointegradoapi.service.impl;

import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatTurmaRequestDTO;
import br.edu.ifs.sistemaacademicointegradoapi.dto.chat.ChatTurmaResponseDTO;
import br.edu.ifs.sistemaacademicointegradoapi.mapper.ChatTurmaMapper;
import br.edu.ifs.sistemaacademicointegradoapi.model.ChatTurma;
import br.edu.ifs.sistemaacademicointegradoapi.model.PerfilEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.StatusEnum;
import br.edu.ifs.sistemaacademicointegradoapi.model.Turma;
import br.edu.ifs.sistemaacademicointegradoapi.repository.ChatTurmaRepository;
import br.edu.ifs.sistemaacademicointegradoapi.service.ChatTurmaService;
import br.edu.ifs.sistemaacademicointegradoapi.service.TurmaService;
import br.edu.ifs.sistemaacademicointegradoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatTurmaServiceImpl implements ChatTurmaService {

    private final ChatTurmaRepository chatTurmaRepository;
    private final ChatTurmaMapper chatTurmaMapper;
    private final TurmaService turmaService;
    private final UsuarioService usuarioService;

    @Override
    public ChatTurma buscarPorId(Long chatTurmaId) {
        return chatTurmaRepository.findById(chatTurmaId)
                .orElseThrow(() -> new RuntimeException("Chat de turma não encontrado."));
    }

    @Override
    public List<ChatTurmaResponseDTO> listarTodos() {
        return chatTurmaRepository.findAll().stream()
                .map(chatTurmaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChatTurmaResponseDTO buscarPorTurma(Long turmaId) {
        // Assume que existe no repositório um método customizado, mas implementamos de forma simplificada por enquanto.
        return chatTurmaRepository.findAll().stream()
                .filter(chat -> chat.getTurma().getId().equals(turmaId))
                .map(chatTurmaMapper::toResponseDTO)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Chat não encontrado para esta turma."));
    }

    @Override
    @Transactional
    public ChatTurmaResponseDTO criar(ChatTurmaRequestDTO request, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);

        Turma turma = turmaService.buscarPorId(request.getTurmaId());

        ChatTurma chatTurma = new ChatTurma();
        chatTurma.setTurma(turma);
        chatTurma.setTitulo(request.getTitulo() != null ? request.getTitulo() : "Chat da Turma " + turma.getDescricao());
        chatTurma.setStatusEnum(StatusEnum.A);

        chatTurma = chatTurmaRepository.save(chatTurma);
        return chatTurmaMapper.toResponseDTO(chatTurma);
    }

    @Override
    @Transactional
    public void inativar(Long id, Long usuarioIdAdministrador) {
        usuarioService.validarPermissao(usuarioIdAdministrador, PerfilEnum.COORDENADOR);
        
        ChatTurma chatTurma = buscarPorId(id);
        chatTurma.setStatusEnum(StatusEnum.I);
        
        chatTurmaRepository.save(chatTurma);
    }
}
