package com.projetosara.sara_api.mapper;

import com.projetosara.sara_api.dto.TipoUsuarioDTO;
import com.projetosara.sara_api.entity.TipoUsuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TipoUsuarioMapper {
    @Mapping(target = "usuarios", ignore = true)
    TipoUsuario toEntity(TipoUsuarioDTO dto);
    TipoUsuarioDTO toDTO(TipoUsuario entity);
}
