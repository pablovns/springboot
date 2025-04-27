package com.pablovns.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "nome_utilizador", length = 100, nullable = false, unique = true)
    private String nomeUtilizador;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "senha", length = 64, nullable = false)
    private String senha;

    @OneToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private UsuarioCategoria categoria;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

}
