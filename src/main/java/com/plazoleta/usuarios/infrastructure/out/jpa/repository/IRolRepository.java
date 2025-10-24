package com.plazoleta.usuarios.infrastructure.out.jpa.repository;

import com.plazoleta.usuarios.infrastructure.out.jpa.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<RolEntity, Long> {
}
