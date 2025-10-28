package com.plazoleta.usuarios.infrastructure.out.jpa.adapter;

import com.plazoleta.usuarios.domain.spi.ILoginAutenticacionPort;
import com.plazoleta.usuarios.infrastructure.configuration.JwtService;
import com.plazoleta.usuarios.infrastructure.exception.LoginClaveInvalidoException;
import com.plazoleta.usuarios.infrastructure.exception.LoginUsuarioInvalidoException;
import com.plazoleta.usuarios.infrastructure.exception.RolNoEncontradoException;
import com.plazoleta.usuarios.infrastructure.out.jpa.entity.RolEntity;
import com.plazoleta.usuarios.infrastructure.out.jpa.entity.UsuarioEntity;
import com.plazoleta.usuarios.infrastructure.out.jpa.repository.IRolRepository;
import com.plazoleta.usuarios.infrastructure.out.jpa.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class LoginJpaAdapter implements ILoginAutenticacionPort {

    private final IUsuarioRepository usuarioRepository;
    private final IRolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String autenticar(String correo, String clave) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCorreo(correo)
                .orElseThrow(()-> new LoginUsuarioInvalidoException("Usuario o clave incorrectos."));

        if (!passwordEncoder.matches(clave,usuarioEntity.getClave())){
            throw new LoginClaveInvalidoException("Usuario o clave incorrectos.");
        }

        RolEntity rol = rolRepository.findById(usuarioEntity.getIdRol())
                .orElseThrow(() -> new RolNoEncontradoException("Rol no encontrado."));

        return jwtService.generarToken(usuarioEntity.getCorreo(),rol.getNombre());
    }
}
