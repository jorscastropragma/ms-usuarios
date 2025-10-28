package com.plazoleta.usuarios.application.handler;

import com.plazoleta.usuarios.application.dto.UsuarioRequest;
import com.plazoleta.usuarios.application.dto.UsuarioResponse;
import com.plazoleta.usuarios.application.mapper.UsuarioRequestMapper;
import com.plazoleta.usuarios.domain.api.IRolServicePort;
import com.plazoleta.usuarios.domain.api.IUsuarioServicePort;
import com.plazoleta.usuarios.domain.model.Rol;
import com.plazoleta.usuarios.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioHandler implements IUsuarioHandler {

    private final IUsuarioServicePort usuarioServicePort;

    private final IRolServicePort rolServicePort;

    private final UsuarioRequestMapper usuarioRequestMapper;

    @Override
    public void guardarUsuario(UsuarioRequest usuarioRequest) {
        usuarioServicePort.guardarUsuario(usuarioRequestMapper.toUsuario(usuarioRequest));
    }

    @Override
    public UsuarioResponse obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioServicePort.obtenerUsuarioPorId(id);
        UsuarioResponse usuarioResponse = usuarioRequestMapper.toUsuarioResponse(usuario);
        Rol rol = rolServicePort.getRol(usuario.getIdRol());
        usuarioResponse.setRol(rol.getNombre());
        return usuarioResponse;
    }
}
