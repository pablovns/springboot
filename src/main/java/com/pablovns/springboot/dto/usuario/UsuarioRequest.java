package com.pablovns.springboot.dto.usuario;

import com.pablovns.springboot.validation.groups.OnCreate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank(groups = OnCreate.class, message = "Nome é obrigatório na criação")
    private String nome;

    @NotBlank(groups = OnCreate.class, message = "Nome de utilizador é obrigatório na criação")
    private String nomeUtilizador;

    @NotBlank(groups = OnCreate.class, message = "Email é obrigatório na criação")
    @Email(groups = OnCreate.class, message = "Email inválido")
    private String email;

    @NotBlank(groups = OnCreate.class, message = "Senha é obrigatória na criação")
    private String senha;

}