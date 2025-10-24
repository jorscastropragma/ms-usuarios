package com.plazoleta.usuarios.infrastructure.exception;

import com.plazoleta.usuarios.domain.exception.UsuarioMenorDeEdadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MENSAJE = "mensaje";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        String message = "Error de validación";
        if (ex.getBindingResult().getFieldError() != null) {
            BindingResult bindingResult = ex.getBindingResult();
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getDefaultMessage();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MENSAJE, message));
    }

    @ExceptionHandler(UsuarioMenorDeEdadException.class)
    public ResponseEntity<Map<String, String>> domainUsuarioMenorDeEdadException(UsuarioMenorDeEdadException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MENSAJE, ex.getMessage()));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MENSAJE, MensajeException.NO_DATA_FOUND.getMensaje()));
    }
}
