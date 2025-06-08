package com.projetosara.sara_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TipoSensorDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @Schema(example = "TEMP")
    private String codigo;

    @Schema(example = "Sensor de temperatura")
    private String descricao;
}