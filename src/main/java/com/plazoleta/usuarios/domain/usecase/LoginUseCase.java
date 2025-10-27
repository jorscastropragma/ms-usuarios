package com.plazoleta.usuarios.domain.usecase;

import com.plazoleta.usuarios.domain.api.ILoginServicePort;
import com.plazoleta.usuarios.domain.spi.ILoginAutenticacionPort;

public class LoginUseCase implements ILoginServicePort {

    private final ILoginAutenticacionPort loginAutenticacionPort;

    public LoginUseCase(ILoginAutenticacionPort loginAutenticacionPort) {
        this.loginAutenticacionPort = loginAutenticacionPort;
    }

    @Override
    public String login(String correo, String clave) {
        return loginAutenticacionPort.autenticar(correo, clave);
    }
}
