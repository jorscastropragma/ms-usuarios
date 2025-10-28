package com.plazoleta.usuarios.infrastructure.exception;

public class RolNoEncontradoException extends RuntimeException {
    public RolNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
