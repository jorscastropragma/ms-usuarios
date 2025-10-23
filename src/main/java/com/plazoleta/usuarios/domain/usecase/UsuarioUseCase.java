package com.plazoleta.usuarios.domain.usecase;

import com.plazoleta.usuarios.domain.model.Usuario;
import com.plazoleta.usuarios.domain.api.IUsuarioServicePort;
import com.plazoleta.usuarios.domain.spi.IUsuarioPersistencePort;

public class UsuarioUseCase implements IUsuarioServicePort {

    private final IUsuarioPersistencePort usuarioPersistencePort;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioPersistencePort.guardarUsuario(usuario);
    }
}
