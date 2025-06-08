package com.projetosara.sara_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlertaDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    private UsuarioDTO usuario;

    @Schema(example = "Possível evento extremo detectado na região")
    private String mensagem;

    private NivelAlertaDTO nivelAlerta;

    private LocalizacaoDTO localizacao;

    @Schema(example = "2025-06-03T10:15:30")
    private LocalDateTime dataHora;
}