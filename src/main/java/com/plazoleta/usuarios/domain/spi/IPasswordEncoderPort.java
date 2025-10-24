package com.plazoleta.usuarios.domain.spi;

public interface IPasswordEncoderPort {
    String encode(CharSequence rawPassword);
}
