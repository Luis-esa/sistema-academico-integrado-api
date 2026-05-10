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
@Table(name = "cht_chat_turma")
public class ChatTurma {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "cht_nr_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tur_nr_id", nullable = false)
    private Turma turma;

    @Column(name = "cht_tx_titulo", length = 150)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "cht_tx_status", nullable = false, length = 1, columnDefinition = "char(1)")
    private StatusEnum statusEnum = StatusEnum.A;

    @Column(name = "cht_dt_criacao", nullable = false, updatable = false, insertable = false)
    private LocalDateTime dataCriacao;

}
