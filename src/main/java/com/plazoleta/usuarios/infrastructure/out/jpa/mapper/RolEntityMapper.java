package com.plazoleta.usuarios.infrastructure.out.jpa.mapper;

import com.plazoleta.usuarios.domain.model.Rol;
import com.plazoleta.usuarios.infrastructure.out.jpa.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RolEntityMapper {

    Rol toRol(RolEntity rolEntity);
}
