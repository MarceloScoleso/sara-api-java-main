package com.projetosara.sara_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TipoUsuarioDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @Schema(example = "ADM", description = "Código único do tipo de usuário")
    private String codigo;

    @Schema(example = "Administrador do sistema", description = "Descrição do tipo de usuário")
    private String descricao;
}