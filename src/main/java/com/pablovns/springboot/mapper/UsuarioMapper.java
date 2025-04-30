package com.pablovns.springboot.mapper;

import com.pablovns.springboot.dto.usuario.UsuarioRequest;
import com.pablovns.springboot.dto.usuario.UsuarioResponse;
import com.pablovns.springboot.model.usuario.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponse toResponse(Usuario entity);

    List<UsuarioResponse> toResponseList(List<Usuario> usuarios);

    Usuario toEntity(UsuarioRequest dto);

    // Mapeia uma entidade existente com os dados do request
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromRequest(UsuarioRequest usuarioRequest, @MappingTarget Usuario usuario);
}
