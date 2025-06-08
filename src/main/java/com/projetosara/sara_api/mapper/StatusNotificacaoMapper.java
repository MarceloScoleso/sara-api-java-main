package com.projetosara.sara_api.mapper;

import com.projetosara.sara_api.dto.StatusNotificacaoDTO;
import com.projetosara.sara_api.entity.StatusNotificacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StatusNotificacaoMapper {
    @Mapping(target = "notificacoes", ignore = true)
    StatusNotificacao toEntity(StatusNotificacaoDTO dto);
    StatusNotificacaoDTO toDTO(StatusNotificacao entity);
}
