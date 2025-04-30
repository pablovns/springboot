package com.pablovns.springboot.dto.usuario;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsuarioResponse {

    private Long id;

    private String nome;

    private String nomeUtilizador;

    private String email;

    private UsuarioCategoriaResponse categoria;

    private LocalDateTime createdAt;

}