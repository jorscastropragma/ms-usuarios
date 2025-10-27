package com.plazoleta.usuarios.application.handler;

import com.plazoleta.usuarios.application.dto.LoginRequest;
import com.plazoleta.usuarios.application.dto.LoginResponse;
import com.plazoleta.usuarios.domain.api.ILoginServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginHandler implements ILoginHandler{

    private final ILoginServicePort loginServicePort;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(loginServicePort.login(loginRequest.getCorreo(), loginRequest.getClave()));
        return loginResponse;
    }
}
