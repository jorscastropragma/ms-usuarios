package com.plazoleta.usuarios.domain.spi;

import com.plazoleta.usuarios.domain.model.Rol;

public interface IRolPersistencePort {
    Rol getRol(Long idRol);
}
