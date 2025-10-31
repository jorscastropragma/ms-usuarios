package com.plazoleta.usuarios.infrastructure.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenExpiradoException extends AuthenticationException {
    public TokenExpiradoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
