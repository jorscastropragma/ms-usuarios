package com.plazoleta.usuarios.domain.exception;

public enum MensajeDomainException {
    USUARIO_MENOR_EDAD("El usuario debe ser mayor de edad.");

    private String mensaje;

    MensajeDomainException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
