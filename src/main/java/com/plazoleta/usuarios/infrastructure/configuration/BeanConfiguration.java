package com.plazoleta.usuarios.infrastructure.configuration;

import com.plazoleta.usuarios.domain.api.IUsuarioServicePort;
import com.plazoleta.usuarios.domain.spi.IPasswordEncoderPort;
import com.plazoleta.usuarios.domain.spi.IUsuarioPersistencePort;
import com.plazoleta.usuarios.domain.usecase.UsuarioUseCase;
import com.plazoleta.usuarios.domain.validations.IUsuarioValidador;
import com.plazoleta.usuarios.domain.validations.UsuarioValidador;
import com.plazoleta.usuarios.infrastructure.out.jpa.adapter.UsuarioJpaAdapter;
import com.plazoleta.usuarios.infrastructure.out.jpa.mapper.UsuarioEntityMapper;
import com.plazoleta.usuarios.infrastructure.out.jpa.repository.IUsuarioRepository;
import com.plazoleta.usuarios.infrastructure.out.security.PasswordEnconderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUsuarioRepository iUsuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;

    @Bean
    public IUsuarioPersistencePort usuarioPersistencePort() {
        return new UsuarioJpaAdapter(iUsuarioRepository,  usuarioEntityMapper);
    }

    @Bean
    public IUsuarioValidador usuarioValidador() {
        return new UsuarioValidador();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IPasswordEncoderPort passwordEncoderPort() {
        return new PasswordEnconderRepository(passwordEncoder());
    }

    @Bean
    public IUsuarioServicePort usuarioServicePort() {
        return new UsuarioUseCase(usuarioPersistencePort(), usuarioValidador(), passwordEncoderPort());
    }
}
