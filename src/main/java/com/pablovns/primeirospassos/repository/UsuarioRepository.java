package com.pablovns.primeirospassos.repository;

import com.pablovns.primeirospassos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
