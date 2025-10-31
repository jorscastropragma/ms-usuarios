package com.plazoleta.usuarios.infrastructure.out.security;

import com.plazoleta.usuarios.infrastructure.exception.MensajePortException;
import com.plazoleta.usuarios.infrastructure.exception.RecursoNoEncontradoException;
import com.plazoleta.usuarios.infrastructure.out.jpa.entity.RolEntity;
import com.plazoleta.usuarios.infrastructure.out.jpa.entity.UsuarioEntity;
import com.plazoleta.usuarios.infrastructure.out.jpa.repository.IRolRepository;
import com.plazoleta.usuarios.infrastructure.out.jpa.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetail implements UserDetailsService {

    private final IUsuarioRepository usuarioRepository;
    private final IRolRepository rolRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCorreo(correo)
                //enums para mensajes
                .orElseThrow(()-> new RecursoNoEncontradoException(MensajePortException.USUARIO_NO_ENCONTRADO.getMensaje()));

        RolEntity rol = rolRepository.findById(usuarioEntity.getIdRol())
                .orElseThrow(() -> new RecursoNoEncontradoException(MensajePortException.ROL_NO_ENCONTRADO.getMensaje()));

        return User.builder()
                .username(usuarioEntity.getCorreo())
                .roles()
                .password(usuarioEntity.getClave())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+rol.getNombre())))
                .build();
    }
}
