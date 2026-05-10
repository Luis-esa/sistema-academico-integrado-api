package br.edu.ifs.sistemaacademicointegradoapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "fal_falta")
public class Falta {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "fal_nr_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mat_nr_id", nullable = false)
    private MatriculaTurma matriculaTurma;

    @Column(name = "fal_dt_aula", nullable = false)
    private LocalDate dataAula;

    @Column(name = "fal_nr_quantidade", nullable = false)
    private Integer quantidadeFaltas = 1;

    @Column(name = "fal_tx_justificativa", columnDefinition = "TEXT")
    private String justificativa;

    @Column(name = "fal_tx_codigo_suap", length = 100)
    private String codigoSuap;

}
