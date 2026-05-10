package br.edu.ifs.sistemaacademicointegradoapi.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tur_turma")
public class Turma {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "tur_nr_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dis_nr_id", nullable = false)
    private Disciplina disciplina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_nr_id", nullable = false)
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pel_nr_id", nullable = false)
    private PeriodoLetivo periodoLetivo;

    @Column(name = "tur_tx_descricao", nullable = false, length = 150)
    private String descricao;

    @Column(name = "tur_tx_codigo_suap", length = 100)
    private String codigoSuap;

    @Enumerated(EnumType.STRING)
    @Column(name = "tur_tx_status", nullable = false, length = 1, columnDefinition = "char(1)")
    private StatusEnum status = StatusEnum.A;

}
