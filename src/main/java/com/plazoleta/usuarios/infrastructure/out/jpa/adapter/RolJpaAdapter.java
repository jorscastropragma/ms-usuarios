package com.plazoleta.usuarios.infrastructure.out.jpa.adapter;

import com.plazoleta.usuarios.domain.model.Rol;
import com.plazoleta.usuarios.domain.spi.IRolPersistencePort;
import com.plazoleta.usuarios.infrastructure.out.jpa.mapper.RolEntityMapper;
import com.plazoleta.usuarios.infrastructure.out.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RolJpaAdapter implements IRolPersistencePort {

    private final IRolRepository rolRepository;

    private final RolEntityMapper rolEntityMapper;

    @Override
    public Rol getRol(Long idRol) {
        return rolEntityMapper.toRol(rolRepository.getReferenceById(idRol));
    }
}
