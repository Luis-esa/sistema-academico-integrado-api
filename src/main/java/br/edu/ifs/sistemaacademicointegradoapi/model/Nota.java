package br.edu.ifs.sistemaacademicointegradoapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "not_nota")
public class Nota {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "not_nr_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mat_nr_id", nullable = false)
    private MatriculaTurma matriculaTurma;

    @Column(name = "not_tx_descricao", nullable = false, length = 100)
    private String Descricao;

    @Column(name = "not_nr_valor", precision = 5, scale = 2)
    private BigDecimal valor;

    @Column(name = "not_nr_peso", precision = 5, scale = 2)
    private BigDecimal peso;

    @Column(name = "not_dt_avaliacao")
    private LocalDate dataAvaliacao;

    @Column(name = "not_tx_codigo_suap", length = 100)
    private String codigoSuap;

}