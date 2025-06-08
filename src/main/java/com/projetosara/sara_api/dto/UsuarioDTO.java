package com.projetosara.sara_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class UsuarioDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @Schema(example = "João Silva")
    private String nome;

    @Schema(example = "joao.silva@email.com")
    private String email;

    @Schema(example = "senhaSegura123")
    private String senha;

    @Schema(example = "12345678901")
    private String cpf;

    private TipoUsuarioDTO tipoUsuario;
}
