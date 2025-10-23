package com.plazoleta.usuarios.domain.validations;

import com.plazoleta.usuarios.domain.Exception.UsuarioMenorDeEdadException;

import java.time.LocalDate;
import java.time.Period;

public class UsuarioValidador  implements IUsuarioValidador{

    public void validaMayorDeEdad(LocalDate fechaNacimiento) {
        int edadMinima = 18;
        if (Period.between(fechaNacimiento, LocalDate.now()).getYears() < edadMinima) {
            throw new UsuarioMenorDeEdadException("El usuario debe ser mayor de edad.");
        }
    }
}
