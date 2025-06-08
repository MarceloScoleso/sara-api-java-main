package com.projetosara.sara_api.mapper;

import com.projetosara.sara_api.dto.AlertaDTO;
import com.projetosara.sara_api.entity.Alerta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlertaMapper {
    @Mapping(target = "localizacao.alertas", ignore = true)
    @Mapping(target = "localizacao.sensores", ignore = true)
    @Mapping(target = "nivelAlerta.alertas", ignore = true)
    @Mapping(target = "usuario.tipoUsuario.usuarios", ignore = true)
    Alerta toEntity(AlertaDTO dto);
    AlertaDTO toDTO(Alerta entity);
}
