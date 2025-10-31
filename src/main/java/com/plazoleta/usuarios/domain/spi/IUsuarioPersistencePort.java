package com.plazoleta.usuarios.domain.spi;

import com.plazoleta.usuarios.domain.model.Usuario;

public interface IUsuarioPersistencePort {
    void guardarUsuario(Usuario usuario);
    Usuario obtenerUsuarioPorId(Long id);
    void guardarEmpleado(Usuario usuario, Long idRestaurante);
}
