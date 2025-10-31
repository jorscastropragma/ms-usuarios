package com.plazoleta.usuarios.infrastructure.out.jpa.repository;

import com.plazoleta.usuarios.infrastructure.out.jpa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
    Optional<UsuarioEntity> findByCorreo(String correo);
    Optional<UsuarioEntity> findByDocumentoIdentidad(Long documento);
}
