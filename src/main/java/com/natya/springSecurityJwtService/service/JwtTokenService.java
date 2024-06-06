package com.natya.springSecurityJwtService.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface JwtTokenService {

    String generateToken(UserDetails userDetails);

    String getUsernames(String token);

    List<String> getRoles(String token);
}
