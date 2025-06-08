package com.projetosara.sara_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class LoginRequestDTO {

    @NotBlank
    @Schema(example = "Marcelo@email.com", description = "Email ou nome de usuário")
    private String username;

    @NotBlank
    @Schema(example = "Marcio12", description = "Senha do usuário")
    private String password;
}