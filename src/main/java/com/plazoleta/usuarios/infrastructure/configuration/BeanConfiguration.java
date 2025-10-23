package com.plazoleta.usuarios.infrastructure.configuration;

import com.plazoleta.usuarios.domain.api.IUsuarioServicePort;
import com.plazoleta.usuarios.domain.spi.IUsuarioPersistencePort;
import com.plazoleta.usuarios.domain.usecase.UsuarioUseCase;
import com.plazoleta.usuarios.domain.validations.IUsuarioValidador;
import com.plazoleta.usuarios.domain.validations.UsuarioValidador;
import com.plazoleta.usuarios.infrastructure.out.jpa.adapter.UsuarioJpaAdapter;
import com.plazoleta.usuarios.infrastructure.out.jpa.mapper.UsuarioEntityMapper;
import com.plazoleta.usuarios.infrastructure.out.jpa.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public IUsuarioServicePort usuarioServicePort() {
        return new UsuarioUseCase(usuarioPersistencePort(), usuarioValidador());
    }
}
