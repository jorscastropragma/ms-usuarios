package com.plazoleta.usuarios.infrastructure.out.jpa.adapter;

import com.plazoleta.usuarios.domain.model.Usuario;
import com.plazoleta.usuarios.domain.spi.IUsuarioPersistencePort;
import com.plazoleta.usuarios.infrastructure.out.jpa.mapper.UsuarioEntityMapper;
import com.plazoleta.usuarios.infrastructure.out.jpa.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioJpaAdapter implements IUsuarioPersistencePort {

    private final IUsuarioRepository usuarioRepository;

    private final UsuarioEntityMapper usuarioEntityMapper;

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuarioEntityMapper.toEntity(usuario));
    }
}
