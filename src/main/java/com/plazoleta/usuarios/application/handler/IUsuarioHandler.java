package com.plazoleta.usuarios.application.handler;

import com.plazoleta.usuarios.application.dto.UsuarioRequest;

public interface IUsuarioHandler {
    void guardarUsuario(UsuarioRequest usuario);
}
