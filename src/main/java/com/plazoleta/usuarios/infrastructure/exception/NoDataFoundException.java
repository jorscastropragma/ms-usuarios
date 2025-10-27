package com.plazoleta.usuarios.infrastructure.exception;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String mensaje) {
        super(mensaje);
    }
}
