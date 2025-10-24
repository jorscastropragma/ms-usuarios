package com.plazoleta.usuarios.domain.usecase;

import com.plazoleta.usuarios.domain.api.IRolServicePort;
import com.plazoleta.usuarios.domain.model.Rol;
import com.plazoleta.usuarios.domain.spi.IRolPersistencePort;

public class RolUseCase implements IRolServicePort {

    private final IRolPersistencePort rolPersistencePort;

    public RolUseCase(IRolPersistencePort rolPersistencePort) {
        this.rolPersistencePort = rolPersistencePort;
    }

    @Override
    public Rol getRol(Long idRol) {
        return rolPersistencePort.getRol(idRol);
    }

}
