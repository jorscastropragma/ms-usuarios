package com.plazoleta.usuarios.domain.Exception;

public class UsuarioMenorDeEdadException extends RuntimeException{
    public UsuarioMenorDeEdadException(String mensaje) {
        super(mensaje);
    }
}
