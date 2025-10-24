package com.plazoleta.usuarios.domain.api;

import com.plazoleta.usuarios.domain.model.Usuario;

public interface IUsuarioServicePort {

    void guardarUsuario(Usuario usuario);

    Usuario obtenerUsuarioPorId(Long id);
}
