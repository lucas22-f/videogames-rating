package com.gamesrating.gamesratingdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gamesrating.gamesratingdemo.jwt.JwtAuthenticationFilter;

import jakarta.servlet.RequestDispatcher;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    private final Oauth2SuccesHandler oauth2SuccesHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest
                        -> authRequest.requestMatchers("/auth/**", "/oauth2/**", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint((req, res, exec) -> {
            req.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, 403);
            req.setAttribute(RequestDispatcher.ERROR_MESSAGE, "Error en las credenciales de ingreso" + exec.getMessage());
            req.getRequestDispatcher("/error").forward(req, res);
        }))
                .oauth2Login(oauth2 -> oauth2
                .successHandler(oauth2SuccesHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
