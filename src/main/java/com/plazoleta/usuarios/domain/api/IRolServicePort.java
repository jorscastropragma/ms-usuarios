package com.plazoleta.usuarios.domain.api;

import com.plazoleta.usuarios.domain.model.Rol;

public interface IRolServicePort {
    Rol getRol(Long idRol);
}
