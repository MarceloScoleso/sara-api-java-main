package com.projetosara.sara_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class StatusNotificacaoDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @Schema(example = "ENVIADO")
    private String codigo;

    @Schema(example = "Notificação enviada com sucesso")
    private String descricao;
}