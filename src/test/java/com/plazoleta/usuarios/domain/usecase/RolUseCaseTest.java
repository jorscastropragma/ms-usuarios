package com.plazoleta.usuarios.domain.usecase;

import com.plazoleta.usuarios.domain.model.Rol;
import com.plazoleta.usuarios.domain.spi.IRolPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RolUseCaseTest {

    @Mock
    private IRolPersistencePort rolPersistencePort;

    @InjectMocks
    private RolUseCase rolUseCase;

    @Test
    void getRol_CuandoElRolExiste() {
        Long idRol = 1L;
        Rol rolEsperado = new Rol(1L, "ADMIN");

        when(rolPersistencePort.getRol(idRol)).thenReturn(rolEsperado);

        Rol resultado = rolUseCase.getRol(idRol);

        assertEquals(rolEsperado, resultado);
        verify(rolPersistencePort, times(1)).getRol(idRol);

    }

    @Test
    void getRol_CuandoElRolNoExiste() {
        Long idRol = 1L;
        Rol rolEsperado = null;

        when(rolPersistencePort.getRol(idRol)).thenReturn(rolEsperado);

        Rol resultado = rolUseCase.getRol(idRol);

        assertEquals(rolEsperado, resultado);
        verify(rolPersistencePort, times(1)).getRol(idRol);
    }
}