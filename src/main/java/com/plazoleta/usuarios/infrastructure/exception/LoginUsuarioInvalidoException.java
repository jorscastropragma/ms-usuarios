package com.plazoleta.usuarios.infrastructure.exception;

public class LoginUsuarioInvalidoException extends RuntimeException {
    public LoginUsuarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}
