package com.plazoleta.usuarios.domain.usecase;

import com.plazoleta.usuarios.domain.model.Usuario;
import com.plazoleta.usuarios.domain.api.IUsuarioServicePort;
import com.plazoleta.usuarios.domain.spi.IPasswordEncoderPort;
import com.plazoleta.usuarios.domain.spi.IUsuarioPersistencePort;
import com.plazoleta.usuarios.domain.validations.UsuarioValidador;


public class UsuarioUseCase implements IUsuarioServicePort {

    private final IUsuarioPersistencePort usuarioPersistencePort;

    private final IPasswordEncoderPort passwordEncoderPort;

    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort,
                          IPasswordEncoderPort passwordEncoderPort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuario.setClave(passwordEncoderPort.encode(usuario.getClave()));
        new UsuarioValidador().validaMayorDeEdad(usuario.getFechaNacimiento());
        usuarioPersistencePort.guardarUsuario(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioPersistencePort.obtenerUsuarioPorId(id);
    }

    @Override
    public void guardarEmpleado(Usuario usuario, Long idRestaurante) {
        usuario.setClave(passwordEncoderPort.encode(usuario.getClave()));
        new UsuarioValidador().validaMayorDeEdad(usuario.getFechaNacimiento());
        usuarioPersistencePort.guardarEmpleado(usuario, idRestaurante);
    }


}
