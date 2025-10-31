package com.plazoleta.usuarios.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter  extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private static final String MENSAJE_TOKEN_EXPIRADO = "El token ha expirado. Inicia sesión nuevamente.";
    private static final String CODIGO_TOKEN_EXPIRADO = "TOKEN_EXPIRADO";
    private static final String MENSAJE_TOKEN_INVALIDO = "El token es invalido. Inicia sesión nuevamente.";
    private static final String CODIGO_TOKEN_INVALIDO = "TOKEN_INVALIDO";
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        String username;

        SecurityContextHolder.clearContext();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setHeader("WWW-Authenticate", "Bearer error=\"invalid_token\"");

        try {
            username = jwtService.obtnerCorreo(token);
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            Map<String, Object> body = Map.of(
                    "mensaje", MENSAJE_TOKEN_EXPIRADO,
                    "codigo",  CODIGO_TOKEN_EXPIRADO
            );
            response.getWriter().write(mapper.writeValueAsString(body));
            return;
        }catch (JwtException e){
            Map<String, Object> body = Map.of(
                    "mensaje", MENSAJE_TOKEN_INVALIDO,
                    "codigo",  CODIGO_TOKEN_INVALIDO
            );
            response.getWriter().write(mapper.writeValueAsString(body));
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (Boolean.TRUE.equals(jwtService.validarToken(token))){
                UsernamePasswordAuthenticationToken autenticacion =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                autenticacion.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(autenticacion);
            }
        }
        filterChain.doFilter(request, response);
    }
}
