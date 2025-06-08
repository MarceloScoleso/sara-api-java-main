package com.projetosara.sara_api.mapper;

import com.projetosara.sara_api.dto.UsuarioDTO;
import com.projetosara.sara_api.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(target = "tipoUsuario.usuarios", ignore = true)
    Usuario toEntity(UsuarioDTO dto);
    UsuarioDTO toDTO(Usuario entity);
}
