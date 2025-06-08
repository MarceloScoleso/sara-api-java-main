package com.projetosara.sara_api.mapper;

import com.projetosara.sara_api.dto.TipoSensorDTO;
import com.projetosara.sara_api.entity.TipoSensor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TipoSensorMapper {
    @Mapping(target = "sensores", ignore = true)
    TipoSensor toEntity(TipoSensorDTO dto);
    TipoSensorDTO toDTO(TipoSensor entity);
}
