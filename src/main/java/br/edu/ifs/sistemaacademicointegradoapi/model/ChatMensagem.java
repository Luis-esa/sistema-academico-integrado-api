package br.edu.ifs.sistemaacademicointegradoapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "msg_chat_mensagem")
public class ChatMensagem {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "msg_nr_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cht_nr_id", nullable = false)
    private ChatTurma chatTurma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_nr_id", nullable = false)
    private Usuario usuario;

    @Column(name = "msg_tx_mensagem", nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @Column(name = "msg_dt_envio", nullable = false, insertable = false, updatable = false)
    private LocalDateTime dataDeEnvio;

    @Enumerated(EnumType.STRING)
    @Column(name = "msg_tx_status", nullable = false, length = 1, columnDefinition = "char(1)")
    private StatusEnum statusEnum = StatusEnum.A;

}
