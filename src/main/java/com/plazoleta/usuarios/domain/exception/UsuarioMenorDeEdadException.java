package com.plazoleta.usuarios.domain.exception;

public class UsuarioMenorDeEdadException extends RuntimeException{
    public UsuarioMenorDeEdadException(String mensaje) {
        super(mensaje);
    }
}
