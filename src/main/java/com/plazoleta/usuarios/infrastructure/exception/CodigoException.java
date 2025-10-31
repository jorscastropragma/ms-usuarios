package com.plazoleta.usuarios.infrastructure.exception;

public enum CodigoException {
    NO_DATA_FOUND("DATOS_NO_ENCONTRADOS"),
    CREDENCIALES_INVALIDAS("CREDENCIALES_INVALIDAS"),
    REGLAS_DE_NEGOCIO_INVALIDAS("REGLAS_DE_NEGOCIO_INVALIDAS"),
    RESTRICCION_RECURSO_YA_EXISTE("RESTRICCION_RECURSO_YA_EXISTE");


    private String codigo;

    CodigoException(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
