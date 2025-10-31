package com.plazoleta.usuarios.infrastructure.exception;

public class RecursoNoEncontradoException extends RuntimeException{
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
