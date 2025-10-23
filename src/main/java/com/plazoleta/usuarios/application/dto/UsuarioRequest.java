package com.plazoleta.usuarios.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UsuarioRequest {
    private String nombre;
    private String apellido;
    private String documentoIdentidad;
    private String celular;
    private Date fechaNacimiento;
    private String correo;
    private String clave;
}
