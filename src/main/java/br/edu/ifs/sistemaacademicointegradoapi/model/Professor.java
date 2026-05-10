package br.edu.ifs.sistemaacademicointegradoapi.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pro_professor")
public class Professor {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "pro_nr_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usu_nr_id", nullable = false)
    private Usuario usuario;

    @Column(name = "pro_tx_matricula_siape", length = 50)
    private String matriculaSiape;

    @Column(name = "pro_tx_suap_id", length = 100)
    private String suapId;

    @Enumerated(EnumType.STRING)
    @Column(name = "pro_tx_status", nullable = false, length = 1, columnDefinition = "char(1)")
    private StatusEnum status = StatusEnum.A;

}
