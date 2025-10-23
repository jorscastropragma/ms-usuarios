package com.plazoleta.usuarios.domain.usecase;

import com.plazoleta.usuarios.domain.Exception.UsuarioMenorDeEdadException;
import com.plazoleta.usuarios.domain.model.Usuario;
import com.plazoleta.usuarios.domain.spi.IUsuarioPersistencePort;
import com.plazoleta.usuarios.domain.validations.IUsuarioValidador;
import com.plazoleta.usuarios.domain.validations.UsuarioValidador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UsuarioUseCaseTest {

    @Mock
    private IUsuarioPersistencePort usuarioPersistencePort;

    @Mock
    private IUsuarioValidador usuarioValidador;

    @InjectMocks
    private UsuarioUseCase usuarioUseCase;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        this.usuario = new Usuario(Long.MIN_VALUE,
                "jose",
                "cortez",
                1144124701L,
                "312586958",
                LocalDate.now().minusYears(20),
                "test@test.com",
                "clave",
                1L);
    }

    @Test
    void guardarUsuarioMayorDeEdad() {

        usuarioUseCase.guardarUsuario(usuario);

        verify(usuarioPersistencePort,times(1)).guardarUsuario(usuario);
    }

    @Test
    void guardarUsuarioMenorDeEdadLanzaException() {

        usuario.setFechaNacimiento(LocalDate.now().minusYears(17));

        doThrow(new UsuarioMenorDeEdadException("El usuario debe ser mayor de edad."))
                .when(usuarioValidador).validaMayorDeEdad(usuario.getFechaNacimiento());

        assertThrows(UsuarioMenorDeEdadException.class, () -> usuarioUseCase.guardarUsuario(usuario));

        verify(usuarioPersistencePort,never()).guardarUsuario(usuario);
    }
}