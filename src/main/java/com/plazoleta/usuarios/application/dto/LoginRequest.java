package com.plazoleta.usuarios.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    private String correo;
    private String clave;
}
