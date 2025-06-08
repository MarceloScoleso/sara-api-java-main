package com.projetosara.sara_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LeituraSensorDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    private SensorDTO sensor;

    @Schema(example = "23.5")
    private Double valor;

    @Schema(example = "°C")
    private String unidade;

    @Schema(example = "2025-06-03T10:15:30")
    private LocalDateTime dataHora;
}