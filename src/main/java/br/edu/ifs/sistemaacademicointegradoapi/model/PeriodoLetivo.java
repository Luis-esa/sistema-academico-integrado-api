package br.edu.ifs.sistemaacademicointegradoapi.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pel_periodo_letivo")
public class PeriodoLetivo {

    @Id
        @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pel_nr_id", nullable = false)
    private Long id;

    @Column(name = "pel_nr_ano", nullable = false, unique = true)
    private  Integer ano;

    @Column(name = "pel_nr_semestre", nullable = false, unique = true)
    private Integer semestre;

    @Column(name = "pel_tx_descricao", nullable = false, length = 50)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "pel_tx_status", nullable = false, length = 1, columnDefinition = "char(1)")
    private StatusEnum status = StatusEnum.A;

}
