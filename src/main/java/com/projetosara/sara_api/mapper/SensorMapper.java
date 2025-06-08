package com.projetosara.sara_api.mapper;

import com.projetosara.sara_api.dto.SensorDTO;
import com.projetosara.sara_api.entity.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SensorMapper {
    @Mapping(target = "tipoSensor.sensores", ignore = true)
    @Mapping(target = "localizacao.alertas", ignore = true)
    @Mapping(target = "localizacao.sensores", ignore = true)
    @Mapping(target = "leituras", ignore = true)
    Sensor toEntity(SensorDTO dto);
    SensorDTO toDTO(Sensor entity);
}
