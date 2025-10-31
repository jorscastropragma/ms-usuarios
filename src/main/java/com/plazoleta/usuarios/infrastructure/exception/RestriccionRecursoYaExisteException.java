package com.plazoleta.usuarios.infrastructure.exception;

public class RestriccionRecursoYaExisteException extends RuntimeException{
    public RestriccionRecursoYaExisteException(String mensaje) {
        super(mensaje);
    }
}
