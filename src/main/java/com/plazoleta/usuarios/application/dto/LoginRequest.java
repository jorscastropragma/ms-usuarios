package com.plazoleta.usuarios.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    @Schema(
            description = "Correo electrónico del usuario",
            example = "juan.perez2@email.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String correo;

    @Schema(
            description = "Calve del usuario",
            example = "miPassword123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String clave;
}
