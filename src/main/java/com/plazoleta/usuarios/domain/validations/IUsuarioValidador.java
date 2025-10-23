package com.plazoleta.usuarios.domain.validations;

import java.time.LocalDate;

public interface IUsuarioValidador {
    void validaMayorDeEdad(LocalDate fechaNacimiento);
}
