package com.projetosara.sara_api.mapper;

import com.projetosara.sara_api.dto.NivelAlertaDTO;
import com.projetosara.sara_api.entity.NivelAlerta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NivelAlertaMapper {
    @Mapping(target = "alertas", ignore = true)
    NivelAlerta toEntity(NivelAlertaDTO dto);
    NivelAlertaDTO toDTO(NivelAlerta entity);
}