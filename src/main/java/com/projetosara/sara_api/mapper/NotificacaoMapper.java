package com.projetosara.sara_api.mapper;

import com.projetosara.sara_api.dto.NotificacaoDTO;
import com.projetosara.sara_api.entity.Notificacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AlertaMapper.class})
public interface NotificacaoMapper {
    @Mapping(target = "usuario.tipoUsuario.usuarios", ignore = true)
    @Mapping(target = "alerta.localizacao.alertas", ignore = true)
    @Mapping(target = "alerta.localizacao.sensores", ignore = true)
    @Mapping(target = "alerta.nivelAlerta.alertas", ignore = true)
    @Mapping(target = "alerta.usuario.tipoUsuario.usuarios", ignore = true)
    @Mapping(target = "status.notificacoes", ignore = true)
    Notificacao toEntity(NotificacaoDTO dto);

    NotificacaoDTO toDTO(Notificacao entity);
}
