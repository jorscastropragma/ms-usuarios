package com.plazoleta.usuarios.domain.spi;

public interface ILoginAutenticacionPort {
    String autenticar(String correo, String clave);
}
