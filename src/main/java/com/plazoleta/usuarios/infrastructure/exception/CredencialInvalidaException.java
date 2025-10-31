package com.plazoleta.usuarios.infrastructure.exception;

public class CredencialInvalidaException extends RuntimeException {
    public CredencialInvalidaException(String mensaje) {
        super(mensaje);
    }
}
