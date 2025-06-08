package com.projetosara.sara_api.mapper;

import com.projetosara.sara_api.dto.LeituraSensorDTO;
import com.projetosara.sara_api.entity.LeituraSensor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeituraSensorMapper {
    @Mapping(target = "sensor.leituras", ignore = true)
    @Mapping(target = "sensor.tipoSensor.sensores", ignore = true)
    @Mapping(target = "sensor.localizacao.alertas", ignore = true)
    @Mapping(target = "sensor.localizacao.sensores", ignore = true)
    LeituraSensor toEntity(LeituraSensorDTO dto);
    LeituraSensorDTO toDTO(LeituraSensor entity);
}
