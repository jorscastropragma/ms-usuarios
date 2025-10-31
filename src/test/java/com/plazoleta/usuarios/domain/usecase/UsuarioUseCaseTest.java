package com.plazoleta.usuarios.domain.usecase;

import com.plazoleta.usuarios.domain.exception.ReglaDeNegocioInvalidaException;
import com.plazoleta.usuarios.domain.model.Usuario;
import com.plazoleta.usuarios.domain.spi.IPasswordEncoderPort;
import com.plazoleta.usuarios.domain.spi.IUsuarioPersistencePort;
import com.plazoleta.usuarios.domain.validations.UsuarioValidador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UsuarioUseCaseTest {

    @Mock
    private IUsuarioPersistencePort usuarioPersistencePort;

    private UsuarioValidador usuarioValidador;

    @Mock
    private IPasswordEncoderPort passwordEncoderPort;

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
                "123",
                1L);
    }

    @Test
    void guardarUsuario_CuandoEsMayorDeEdad() {
        String claveEncriptada = ">123<";

        when(passwordEncoderPort.encode("123")).thenReturn(claveEncriptada);

        usuarioUseCase.guardarUsuario(usuario);

        verify(passwordEncoderPort,times(1)).encode("123");
        verify(usuarioValidador,times(1)).validaMayorDeEdad(usuario.getFechaNacimiento());
        verify(usuarioPersistencePort,times(1)).guardarUsuario(usuario);
    }

    @Test
    void guardarUsuario_CuandoEsMenorDeEdad() {

        usuario.setFechaNacimiento(LocalDate.now().minusYears(17));
        String claveEncriptada = ">123<";

        when(passwordEncoderPort.encode("123")).thenReturn(claveEncriptada);

        doThrow(new ReglaDeNegocioInvalidaException("El usuario debe ser mayor de edad."))
                .when(usuarioValidador).validaMayorDeEdad(usuario.getFechaNacimiento());

        ReglaDeNegocioInvalidaException exception = assertThrows(ReglaDeNegocioInvalidaException.class,
                () -> usuarioUseCase.guardarUsuario(usuario));

        assertEquals("El usuario debe ser mayor de edad.",exception.getMessage());

        verify(passwordEncoderPort,times(1)).encode("123");
        verify(usuarioValidador,times(1)).validaMayorDeEdad(usuario.getFechaNacimiento());
        verify(usuarioPersistencePort,never()).guardarUsuario(usuario);
    }

    @Test
    void obtenerUsuarioPorId(){
        Long id = usuario.getId();
        Usuario usuarioEsperado = usuario;

        when(usuarioPersistencePort.obtenerUsuarioPorId(id)).thenReturn(usuarioEsperado);

        Usuario usuario1 = usuarioUseCase.obtenerUsuarioPorId(id);

        assertEquals(usuarioEsperado,usuario1);

        verify(usuarioPersistencePort,times(1)).obtenerUsuarioPorId(id);
    }
}