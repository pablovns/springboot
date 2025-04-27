package com.pablovns.springboot.enums;

import lombok.Getter;

@Getter
public enum CategoriaUsuario {

    USUARIO("Usuário"),
    GERENTE("Gerente"),
    ADMIN("Administrador"),
    ;

    private final String descricao;

    CategoriaUsuario(String descricao) {
        this.descricao = descricao;
    }

}
