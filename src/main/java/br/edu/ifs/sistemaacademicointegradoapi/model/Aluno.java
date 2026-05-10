package br.edu.ifs.sistemaacademicointegradoapi.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "alu_aluno")
public class Aluno {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "alu_nr_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usu_nr_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cur_nr_id")
    private Curso curso;

    @Column(name = "alu_tx_matricula", nullable = false, length = 50, unique = true)
    private String matricula;

    @Column(name = "alu_tx_suap_id", length = 100)
    private String suapId;

    @Enumerated(EnumType.STRING)
    @Column(name = "alu_tx_status", nullable = false, length = 1, columnDefinition = "char(1)")
    private StatusEnum status = StatusEnum.A;

}
