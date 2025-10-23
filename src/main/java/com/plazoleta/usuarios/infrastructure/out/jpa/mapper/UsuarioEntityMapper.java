package com.plazoleta.usuarios.infrastructure.out.jpa.mapper;

import com.plazoleta.usuarios.domain.model.Usuario;
import com.plazoleta.usuarios.infrastructure.out.jpa.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UsuarioEntityMapper {

    UsuarioEntity toEntity(Usuario usuario);

}
