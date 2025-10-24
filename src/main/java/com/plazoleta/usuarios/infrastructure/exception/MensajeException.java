package com.plazoleta.usuarios.infrastructure.exception;

public enum MensajeException {
    NO_DATA_FOUND("No se encontraron datos.");

    private String mensaje;

    MensajeException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
