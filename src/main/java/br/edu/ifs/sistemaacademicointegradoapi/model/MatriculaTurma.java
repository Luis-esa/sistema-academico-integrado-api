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
@Table(name = "mat_matricula_turma")
public class MatriculaTurma {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "mat_nr_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alu_nr_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tur_nr_id", nullable = false)
    private Turma turma;

    @Enumerated(EnumType.STRING)
    @Column(name = "mat_tx_situacao", length = 50)
    private SituacaoMatriculaEnum situacaoMatriculaEnum;

    @Column(name = "mat_dt_matricula")
    private LocalDate dataMatricula;

    @Enumerated(EnumType.STRING)
    @Column(name = "mat_tx_status", nullable = false,length = 1, columnDefinition = "char(1)")
    private StatusEnum statusEnum = StatusEnum.A;

}
