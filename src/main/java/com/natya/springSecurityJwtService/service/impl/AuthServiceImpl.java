package com.natya.springSecurityJwtService.service.impl;

import com.natya.springSecurityJwtService.dto.JwtRequest;
import com.natya.springSecurityJwtService.dto.JwtResponse;
import com.natya.springSecurityJwtService.dto.RegistrationUserDto;
import com.natya.springSecurityJwtService.dto.UserDto;
import com.natya.springSecurityJwtService.exceptions.AppError;
import com.natya.springSecurityJwtService.service.AuthService;
import com.natya.springSecurityJwtService.service.JwtTokenService;
import com.natya.springSecurityJwtService.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService userDetailsService;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public ResponseEntity<?> createAuthToken(JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.name(), authRequest.password()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(AppError.builder()
                    .status(HttpStatus.UNAUTHORIZED.value())
                    .message("UNAUTHORIZED")
                    .build(), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.name());
        String token = jwtTokenService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Override
    public ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.password().equals(registrationUserDto.confirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают", LocalDateTime.now()),
                    HttpStatus.BAD_REQUEST);
        }
        UserDto user = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(user);
    }
}
