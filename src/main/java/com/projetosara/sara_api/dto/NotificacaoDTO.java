package com.projetosara.sara_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificacaoDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    private UsuarioDTO usuario;

    private AlertaDTO alerta;

    private StatusNotificacaoDTO status;

    @Schema(example = "2025-06-03T10:15:30")
    private LocalDateTime dataEnvio;
}