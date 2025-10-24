package com.plazoleta.usuarios.application.handler;

import com.plazoleta.usuarios.application.dto.UsuarioRequest;
import com.plazoleta.usuarios.application.dto.UsuarioResponse;

public interface IUsuarioHandler {
    void guardarUsuario(UsuarioRequest usuario);
    UsuarioResponse obtenerUsuarioPorId(Long id);
}
