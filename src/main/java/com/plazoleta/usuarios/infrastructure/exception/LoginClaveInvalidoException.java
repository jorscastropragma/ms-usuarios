package com.plazoleta.usuarios.infrastructure.exception;

public class LoginClaveInvalidoException extends RuntimeException {
    public LoginClaveInvalidoException(String mensaje) {
        super(mensaje);
    }
}
