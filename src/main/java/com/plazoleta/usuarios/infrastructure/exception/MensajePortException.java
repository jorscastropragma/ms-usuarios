package com.plazoleta.usuarios.infrastructure.exception;

public enum MensajePortException {
    USUARIO_NO_ENCONTRADO("Usuario no encontrado"),
    ROL_NO_ENCONTRADO("ROL no encontrado"),
    CORREO_CLAVE_INVALIDA("Correo o clave invalida."),
    CORREO_YA_EXISTE("Correo ya registrado."),
    DOCUMENTO_YA_EXISTE("Documento ya registrado.");

    private String mensaje;

    MensajePortException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
