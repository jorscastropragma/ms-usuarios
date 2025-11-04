package com.plazoleta.usuarios.infrastructure.configuration;

import com.plazoleta.usuarios.domain.api.IRolServicePort;
import com.plazoleta.usuarios.domain.api.IUsuarioServicePort;
import com.plazoleta.usuarios.domain.spi.IPasswordEncoderPort;
import com.plazoleta.usuarios.domain.spi.IRolPersistencePort;
import com.plazoleta.usuarios.domain.spi.IUsuarioPersistencePort;
import com.plazoleta.usuarios.domain.usecase.RolUseCase;
import com.plazoleta.usuarios.domain.usecase.UsuarioUseCase;
import com.plazoleta.usuarios.infrastructure.out.jpa.adapter.RolJpaAdapter;
import com.plazoleta.usuarios.infrastructure.out.jpa.adapter.UsuarioJpaAdapter;
import com.plazoleta.usuarios.infrastructure.out.jpa.mapper.RolEntityMapper;
import com.plazoleta.usuarios.infrastructure.out.jpa.mapper.UsuarioEntityMapper;
import com.plazoleta.usuarios.infrastructure.out.jpa.repository.IRolRepository;
import com.plazoleta.usuarios.infrastructure.out.jpa.repository.IUsuarioRepository;
import com.plazoleta.usuarios.infrastructure.out.restconsumer.feign.EmpleadoRestauranteFeignClient;
import com.plazoleta.usuarios.infrastructure.out.security.PasswordEnconderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final EmpleadoRestauranteFeignClient empleadoRestauranteFeignClient;
    private final IUsuarioRepository iUsuarioRepository;
    private final UsuarioEntityMapper usuarioEntityMapper;

    private final IRolRepository rolRepository;
    private final RolEntityMapper rolEntityMapper;


    @Bean
    public IUsuarioPersistencePort usuarioPersistencePort() {
        return new UsuarioJpaAdapter(iUsuarioRepository, empleadoRestauranteFeignClient, usuarioEntityMapper);
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
        return new UsuarioUseCase(usuarioPersistencePort(), passwordEncoderPort());
    }

    @Bean
    public IRolPersistencePort rolPersistencePort() {
        return new RolJpaAdapter(rolRepository, rolEntityMapper);
    }

    @Bean
    public IRolServicePort rolServicePort() {
        return new RolUseCase(rolPersistencePort());
    }

}
