package com.plazoleta.usuarios.infrastructure.exception;

public class ProcesoFallidoException extends RuntimeException{
    public ProcesoFallidoException(String mensaje) {
        super(mensaje);
    }
}
