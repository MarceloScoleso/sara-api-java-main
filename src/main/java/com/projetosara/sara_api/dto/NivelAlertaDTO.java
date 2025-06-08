package com.projetosara.sara_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NivelAlertaDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @Schema(example = "ALTO")
    private String codigo;

    @Schema(example = "Nível alto de alerta")
    private String descricao;
}