package com.pablovns.springboot.model;

import com.pablovns.springboot.enums.CategoriaUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoriaUsuario descricao;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

}
