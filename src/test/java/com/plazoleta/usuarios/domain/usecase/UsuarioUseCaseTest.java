package com.plazoleta.usuarios.domain.usecase;

import com.plazoleta.usuarios.domain.model.Usuario;
import com.plazoleta.usuarios.domain.spi.IUsuarioPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioUseCaseTest {

    @Mock
    private IUsuarioPersistencePort usuarioPersistencePort;

    @InjectMocks
    private UsuarioUseCase usuarioUseCase;

    @Test
    void guardarUsuario() {

        Usuario usuario = new Usuario(Long.MIN_VALUE,
                "jose",
                "cortez",
                "12467654",
                "312586958",
                new Date(),
                "test@test.com",
                "clave");

        usuarioUseCase.guardarUsuario(usuario);

        verify(usuarioPersistencePort,times(1)).guardarUsuario(usuario);
    }
}