package com.plazoleta.usuarios.domain.api;

public interface ILoginServicePort {
    String login(String correo, String clave);
}
