package com.plazoleta.usuarios.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioRequest {
    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio.")
    private String apellido;

    @NotNull(message = "El documento de identidad es obligatorio.")
    private Long documentoIdentidad;

    @NotBlank(message = "El celular es obligatorio")
    @Pattern(regexp = "^\\+?\\d{10,13}$", message = "El teléfono debe contener un máximo de 13 caracteres y puede contener el símbolo +")
    private String celular;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "El correo debe ser válido.")
    private String correo;

    @NotBlank(message = "La clave es obligatoria.")
    private String clave;

    @Schema(hidden = true)
    private Long idRol = 1L;
}
