package com.plazoleta.usuarios.infrastructure.out.jpa.adapter;

import com.plazoleta.usuarios.domain.spi.ILoginAutenticacionPort;
import com.plazoleta.usuarios.infrastructure.configuration.JwtService;
import com.plazoleta.usuarios.infrastructure.exception.NoDataFoundException;
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
                .orElseThrow(()-> new NoDataFoundException("Usuario no encontrado."));

        if (!passwordEncoder.matches(clave,usuarioEntity.getClave())){
            throw new NoDataFoundException("Clave incorrecta.");
        }

        RolEntity rol = rolRepository.findById(usuarioEntity.getIdRol())
                .orElseThrow(() -> new NoDataFoundException("Rol no encontrado."));

        return jwtService.generarToken(usuarioEntity.getCorreo(),rol.getNombreRol());
    }
}
