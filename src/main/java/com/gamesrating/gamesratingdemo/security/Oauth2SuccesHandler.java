package com.gamesrating.gamesratingdemo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gamesrating.gamesratingdemo.enums.Role;
import com.gamesrating.gamesratingdemo.jwt.JwtService;
import com.gamesrating.gamesratingdemo.models.Usuario;
import com.gamesrating.gamesratingdemo.repository.IUsuarioRepository;

import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Oauth2SuccesHandler implements AuthenticationSuccessHandler {

    @Autowired
    private final IUsuarioRepository repositorioUsuario;
    private final JwtService jwtservice;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken oauth2token = (OAuth2AuthenticationToken) authentication;
        String email = oauth2token.getPrincipal().getAttribute("email");
        String token;
        Usuario usuario = repositorioUsuario.findByEmail(email).orElseGet(() -> {

            Usuario nuevUsuario = new Usuario();
            nuevUsuario.setNombre(oauth2token.getPrincipal().getAttribute("name"));
            nuevUsuario.setEmail(email);
            nuevUsuario.setPassword(passwordEncoder.encode((UUID.randomUUID().toString())));
            nuevUsuario.setListaCalificaciones(new ArrayList<>());
            nuevUsuario.setListaComentarios(new ArrayList<>());
            nuevUsuario.setRol(Role.USER);

            return repositorioUsuario.save(nuevUsuario);

        });

        token = jwtservice.getToken(usuario);

        Map<String, Object> mapauser = new HashMap<>();
        mapauser.put("name", usuario.getNombre());
        mapauser.put("email", usuario.getEmail());
        mapauser.put("role", usuario.getRol());

        Map<String, Object> mapares = new HashMap<>();
        if (token != null) {

            mapares.put("token", token);
            mapares.put("exp", jwtservice.getClaim(token, Claims::getExpiration));
            mapares.put("user", mapauser);
        }

        response.setContentType("application/json");
        ObjectMapper objmpa = new ObjectMapper();
        objmpa.registerModule(new JavaTimeModule());
        objmpa.writeValue(response.getWriter(), mapares);

    }

}
