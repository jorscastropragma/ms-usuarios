package com.plazoleta.usuarios.domain.usecase;

import com.plazoleta.usuarios.domain.model.Usuario;
import com.plazoleta.usuarios.domain.api.IUsuarioServicePort;
import com.plazoleta.usuarios.domain.spi.IPasswordEncoderPort;
import com.plazoleta.usuarios.domain.spi.IUsuarioPersistencePort;
import com.plazoleta.usuarios.domain.validations.IUsuarioValidador;


public class UsuarioUseCase implements IUsuarioServicePort {

    private final IUsuarioPersistencePort usuarioPersistencePort;

    private final IUsuarioValidador usuarioValidador;

    private final IPasswordEncoderPort passwordEncoderPort;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort,
                          IUsuarioValidador usuarioValidador,
                          IPasswordEncoderPort passwordEncoderPort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.usuarioValidador = usuarioValidador;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuario.setClave(passwordEncoderPort.encode(usuario.getClave()));
        usuarioValidador.validaMayorDeEdad(usuario.getFechaNacimiento());
        usuarioPersistencePort.guardarUsuario(usuario);
    }
}
