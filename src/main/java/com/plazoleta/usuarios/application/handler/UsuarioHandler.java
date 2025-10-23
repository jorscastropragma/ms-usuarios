package com.plazoleta.usuarios.application.handler;

import com.plazoleta.usuarios.application.dto.UsuarioRequest;
import com.plazoleta.usuarios.application.mapper.UsuarioRequestMapper;
import com.plazoleta.usuarios.domain.api.IUsuarioServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioHandler implements IUsuarioHandler {

    private final IUsuarioServicePort usuarioServicePort;

    private final UsuarioRequestMapper usuarioRequestMapper;

    @Override
    public void guardarUsuario(UsuarioRequest usuarioRequest) {
        usuarioServicePort.guardarUsuario(usuarioRequestMapper.toUsuario(usuarioRequest));
    }
}
