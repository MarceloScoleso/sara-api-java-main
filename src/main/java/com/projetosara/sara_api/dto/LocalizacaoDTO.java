package com.projetosara.sara_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LocalizacaoDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @Schema(example = "São Paulo")
    private String cidade;

    @Schema(example = "SP")
    private String estado;
}