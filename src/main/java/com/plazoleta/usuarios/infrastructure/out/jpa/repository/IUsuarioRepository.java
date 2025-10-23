package com.plazoleta.usuarios.infrastructure.out.jpa.repository;

import com.plazoleta.usuarios.infrastructure.out.jpa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
}
