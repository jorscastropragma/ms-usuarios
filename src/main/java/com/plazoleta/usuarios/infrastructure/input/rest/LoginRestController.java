package com.plazoleta.usuarios.infrastructure.input.rest;

import com.plazoleta.usuarios.application.dto.LoginRequest;
import com.plazoleta.usuarios.application.dto.LoginResponse;
import com.plazoleta.usuarios.application.handler.ILoginHandler;
import com.plazoleta.usuarios.infrastructure.exception.MensajeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Tag(name = "Login", description = "Login de la aplicacion. JWT")
public class LoginRestController {

    private final ILoginHandler loginHandler;

    @Operation(summary = "Login de usuario",
                description="Login de usuario en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                description = "Login exitoso",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "403",
                description = "Acceso denegado",
                content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = """
                        {
                            "mensaje": "Usuario o clave incorrectos."
                        }
                        """)))
    })
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(loginHandler.login(loginRequest));
    }
}
