package com.plazoleta.usuarios.domain.validations;

import com.plazoleta.usuarios.domain.exception.MensajeDomainException;
import com.plazoleta.usuarios.domain.exception.ReglaDeNegocioInvalidaException;

import java.time.LocalDate;
import java.time.Period;

public class UsuarioValidador{

    public void validaMayorDeEdad(LocalDate fechaNacimiento) {
        int edadMinima = 18;
        if (Period.between(fechaNacimiento, LocalDate.now()).getYears() < edadMinima) {
            throw new ReglaDeNegocioInvalidaException(MensajeDomainException.USUARIO_MENOR_EDAD.getMensaje());
        }
    }
}
