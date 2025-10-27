package com.plazoleta.usuarios.application.handler;

import com.plazoleta.usuarios.application.dto.LoginRequest;
import com.plazoleta.usuarios.application.dto.LoginResponse;

public interface ILoginHandler {
    LoginResponse login(LoginRequest loginRequest);
}
