package com.plazoleta.usuarios.infrastructure.out.jpa.adapter;

import com.plazoleta.usuarios.domain.model.Usuario;
import com.plazoleta.usuarios.domain.spi.IUsuarioPersistencePort;
import com.plazoleta.usuarios.infrastructure.exception.MensajePortException;
import com.plazoleta.usuarios.infrastructure.exception.ProcesoFallidoException;
import com.plazoleta.usuarios.infrastructure.exception.RecursoNoEncontradoException;
import com.plazoleta.usuarios.infrastructure.exception.RestriccionRecursoYaExisteException;
import com.plazoleta.usuarios.infrastructure.out.jpa.entity.UsuarioEntity;
import com.plazoleta.usuarios.infrastructure.out.jpa.mapper.UsuarioEntityMapper;
import com.plazoleta.usuarios.infrastructure.out.jpa.repository.IUsuarioRepository;
import com.plazoleta.usuarios.infrastructure.out.restconsumer.dto.EmpleadoRestauranteDto;
import com.plazoleta.usuarios.infrastructure.out.restconsumer.feign.EmpleadoRestauranteFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioJpaAdapter implements IUsuarioPersistencePort {

    private final IUsuarioRepository usuarioRepository;
    private final EmpleadoRestauranteFeignClient empleadoRestauranteFeignClient;
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

    @Override
    public void guardarEmpleado(Usuario usuario, Long idRestaurante) {
        if(usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()){
            throw new RestriccionRecursoYaExisteException(MensajePortException.CORREO_YA_EXISTE.getMensaje());
        }
        if (usuarioRepository.findByDocumentoIdentidad(usuario.getDocumentoIdentidad()).isPresent()){
            throw new RestriccionRecursoYaExisteException(MensajePortException.DOCUMENTO_YA_EXISTE.getMensaje());
        }
        try {
            UsuarioEntity empleado = usuarioRepository.save(usuarioEntityMapper.toEntity(usuario));
            try {
                EmpleadoRestauranteDto empleadoRestauranteDto = new EmpleadoRestauranteDto();
                empleadoRestauranteDto.setIdEmpleado(empleado.getId());
                empleadoRestauranteDto.setIdRestaurante(idRestaurante);
                empleadoRestauranteFeignClient.guardarEmpleadoRestaurante(empleadoRestauranteDto);
            } catch (Exception e) {
                usuarioRepository.deleteById(usuario.getId());
                throw new ProcesoFallidoException(MensajePortException.EMPLEADO_NO_CREADO.getMensaje());
            }
        } catch (Exception e) {
            throw new ProcesoFallidoException(MensajePortException.EMPLEADO_NO_CREADO.getMensaje());
        }

    }
}
