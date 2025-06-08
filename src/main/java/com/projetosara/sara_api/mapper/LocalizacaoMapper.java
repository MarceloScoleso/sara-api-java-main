package com.projetosara.sara_api.mapper;

import com.projetosara.sara_api.dto.LocalizacaoDTO;
import com.projetosara.sara_api.entity.Localizacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocalizacaoMapper {

    @Mapping(target = "alertas", ignore = true)
    @Mapping(target = "sensores", ignore = true)
    Localizacao toEntity(LocalizacaoDTO dto);   

    LocalizacaoDTO toDTO(Localizacao entity);
}