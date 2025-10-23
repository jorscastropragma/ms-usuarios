package com.plazoleta.usuarios.domain.usecase;

import com.plazoleta.usuarios.domain.model.Usuario;
import com.plazoleta.usuarios.domain.api.IUsuarioServicePort;
import com.plazoleta.usuarios.domain.spi.IUsuarioPersistencePort;
import com.plazoleta.usuarios.domain.validations.IUsuarioValidador;


public class UsuarioUseCase implements IUsuarioServicePort {

    private final IUsuarioPersistencePort usuarioPersistencePort;

    private final IUsuarioValidador usuarioValidador;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort, IUsuarioValidador usuarioValidador) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.usuarioValidador = usuarioValidador;
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioValidador.validaMayorDeEdad(usuario.getFechaNacimiento());
        usuarioPersistencePort.guardarUsuario(usuario);
    }
}
