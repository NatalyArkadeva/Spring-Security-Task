package com.natya.springSecurityJwtService.security;

import com.natya.springSecurityJwtService.service.JwtTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwtToken = authHeader.substring(7);
            try {
                username = jwtTokenService.getUsernames(jwtToken);
            } catch (ExpiredJwtException e) {
                log.debug("Token has expired");
            } catch (SignatureException e) {
                log.debug("Sign is incorrect");
            }
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    jwtTokenService.getRoles(jwtToken).stream().map(SimpleGrantedAuthority::new).toList());
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
}
