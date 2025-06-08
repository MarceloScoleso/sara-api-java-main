package com.projetosara.sara_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SensorDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    private TipoSensorDTO tipoSensor;

    private LocalizacaoDTO localizacao;
}