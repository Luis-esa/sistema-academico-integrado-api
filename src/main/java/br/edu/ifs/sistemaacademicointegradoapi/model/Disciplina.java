package br.edu.ifs.sistemaacademicointegradoapi.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "dis_disciplina")
public class Disciplina {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "dis_nr_id", nullable = false)
    private Long id;

    @Column(name = "dis_tx_nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "dis_tx_codigo", length = 50)
    private String codigo;

    @Column(name = "dis_tx_codigo_suap", length = 100)
    private String codigoSuap;

    @Column(name = "dis_nr_carga_horaria")
    private Integer cargaHoraria;

    @Enumerated(EnumType.STRING)
    @Column(name = "dis_tx_status", nullable = false, length = 1, columnDefinition = "char(1)")
    private StatusEnum status = StatusEnum.A;

}
