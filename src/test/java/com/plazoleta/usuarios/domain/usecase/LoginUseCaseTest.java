package com.plazoleta.usuarios.domain.usecase;

import com.plazoleta.usuarios.domain.spi.ILoginAutenticacionPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseTest {

    @InjectMocks
    private LoginUseCase loginUseCase;

    @Mock
    private ILoginAutenticacionPort loginAutenticacionPort;

    @Test
    void test_login() {
        String correo = "correo@test.com";
        String clave = "123";

        when(loginAutenticacionPort.autenticar(correo,clave)).thenReturn("token");

        String token = loginUseCase.login(correo, clave);

        assertNotNull(token);
    }
}