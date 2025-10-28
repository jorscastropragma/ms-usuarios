package com.plazoleta.usuarios.infrastructure.input.rest;

import com.plazoleta.usuarios.application.dto.UsuarioRequest;
import com.plazoleta.usuarios.application.dto.UsuarioResponse;
import com.plazoleta.usuarios.application.handler.IUsuarioHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final IUsuarioHandler usuarioHandler;

    @Operation(summary = "Crear un usuario",
                description="Crear un nuevo usuario en el sistema. Todos los campos son obligatoris" +
                        "El usuario debe ser mayor de edad. los roles 1=ADMINISTRADOR, 2=PROPIETARIO" +
                        "3= EMPLEADO" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Usuario creado",
                    content = @Content(
                            mediaType = "application/json"
                    )),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos no validos",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })
    @PostMapping("/propietario")
    public ResponseEntity<Void> crearUsuarioPropietario(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        usuarioRequest.setIdRol(2L);
        usuarioHandler.guardarUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/empleado")
    public ResponseEntity<Void> crearUsuarioEmpleado(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        usuarioRequest.setIdRol(3L);
        usuarioHandler.guardarUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Obtener usuario por id",
            description="Obtener los datos del usuario con su id" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuario",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponse.class)
                    )),
            @ApiResponse(
                    responseCode = "400",
                    description = "No se encontraron datos",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerUsuarioPorId(@PathVariable Long id) {
        UsuarioResponse usuarioResponse = usuarioHandler.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuarioResponse);
    }
}
