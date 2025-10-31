package com.plazoleta.usuarios.infrastructure.input.rest;

import com.plazoleta.usuarios.application.dto.UsuarioRequest;
import com.plazoleta.usuarios.application.dto.UsuarioResponse;
import com.plazoleta.usuarios.application.handler.IUsuarioHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Gestión de Usuario", description = "Operaciones para administrar usuarios")
public class UsuarioRestController {

    private final IUsuarioHandler usuarioHandler;

    @Operation(summary = "Crear un usuario propietario",
                description="Crear un nuevo usuario con rol propietario en el sistema. Todos los campos son obligatorios" +
                        "El usuario debe ser mayor de edad." )
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

    @Operation(summary = "Crear un usuario empleado",
            description="Crear un nuevo usuario con rol empleado en el sistema. Todos los campos son obligatorios" +
                    "El usuario debe ser mayor de edad." )
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
    @PostMapping("/empleado")
    ///validar quien es el propietario
    public ResponseEntity<Void> crearUsuarioEmpleado(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        usuarioRequest.setIdRol(3L);
        usuarioHandler.guardarUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Crear un cliente",
            description="Crear un nuevo usuario con rol cliente en el sistema. Todos los campos son obligatorios" +
                    "El cliente debe ser mayor de edad." )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Cliente creado",
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
    @PostMapping("/cliente")
    public ResponseEntity<Void> crearUsuarioCliente(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        usuarioRequest.setIdRol(4L);
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
