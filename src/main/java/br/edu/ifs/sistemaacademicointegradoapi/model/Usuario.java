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
@Table(name = "usu_usuario")
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "usu_nr_id", nullable = false)
    private Long id;

    @Column(name = "usu_tx_nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "usu_tx_email", nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "usu_tx_login", nullable = false, length = 100, unique = true)
    private String login;

    @Column(name = "usu_tx_senha", length = 255)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "usu_tx_perfil", nullable = false, length = 30)
    private PerfilEnum perfil;

    @Column(name = "usu_tx_suap_id", length = 100)
    private String suapId;

    @Enumerated(EnumType.STRING)
    @Column(name = "usu_tx_status", nullable = false, length = 1, columnDefinition = "char(1)")
    private StatusEnum status = StatusEnum.A;

    @Column(name = "usu_dt_cadastro", nullable = false, updatable = false, insertable = false)
    private LocalDateTime dataCadastro;
}
