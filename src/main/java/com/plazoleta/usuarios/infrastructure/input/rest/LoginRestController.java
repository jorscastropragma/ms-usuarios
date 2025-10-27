package com.plazoleta.usuarios.infrastructure.input.rest;

import com.plazoleta.usuarios.application.dto.LoginRequest;
import com.plazoleta.usuarios.application.dto.LoginResponse;
import com.plazoleta.usuarios.application.handler.ILoginHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginRestController {

    private final ILoginHandler loginHandler;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(loginHandler.login(loginRequest));
    }
}
