package br.edu.ifs.sistemaacademicointegradoapi.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cur_curso")
public class Curso {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "cur_nr_id", nullable = false)
    private Long id;

    @Column(name = "cur_tx_nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "cur_tx_codigo_suap", length = 100)
    private String codigoSuap;

    @Enumerated(EnumType.STRING)
    @Column(name = "cur_tx_status", nullable = false, length = 1, columnDefinition = "char(1)")
    private StatusEnum status = StatusEnum.A;

}
