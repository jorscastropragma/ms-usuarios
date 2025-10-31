package com.plazoleta.usuarios.infrastructure.out.jpa.adapter;

import com.plazoleta.usuarios.domain.model.Usuario;
import com.plazoleta.usuarios.domain.spi.IUsuarioPersistencePort;
import com.plazoleta.usuarios.infrastructure.exception.MensajePortException;
import com.plazoleta.usuarios.infrastructure.exception.RecursoNoEncontradoException;
import com.plazoleta.usuarios.infrastructure.exception.RestriccionRecursoYaExisteException;
import com.plazoleta.usuarios.infrastructure.out.jpa.mapper.UsuarioEntityMapper;
import com.plazoleta.usuarios.infrastructure.out.jpa.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioJpaAdapter implements IUsuarioPersistencePort {

    private final IUsuarioRepository usuarioRepository;

    private final UsuarioEntityMapper usuarioEntityMapper;

    @Override
    public void guardarUsuario(Usuario usuario) {
        if(usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()){
            throw new RestriccionRecursoYaExisteException(MensajePortException.CORREO_YA_EXISTE.getMensaje());
        }
        if (usuarioRepository.findByDocumentoIdentidad(usuario.getDocumentoIdentidad()).isPresent()){
            throw new RestriccionRecursoYaExisteException(MensajePortException.DOCUMENTO_YA_EXISTE.getMensaje());
        }
        usuarioRepository.save(usuarioEntityMapper.toEntity(usuario));
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioEntityMapper.toUsuario(usuarioRepository.findById(id).
                orElseThrow(()->new RecursoNoEncontradoException(MensajePortException.USUARIO_NO_ENCONTRADO.getMensaje())));
    }
}
