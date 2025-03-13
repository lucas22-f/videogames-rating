package com.gamesrating.gamesratingdemo.jwt;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String token = getTokenFromRequest(request);
        
                if(token == null){
                    filterChain.doFilter(request, response);
                    return;
                }
        
                filterChain.doFilter(request, response);
        
        
            }
        
            public String getTokenFromRequest(HttpServletRequest request) {
                final String authHeader = request.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);
                if(org.springframework.util.StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
                    return authHeader.substring(7);
                }
                return null;
            }
    
}
