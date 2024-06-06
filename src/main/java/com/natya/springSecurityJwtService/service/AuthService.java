package com.natya.springSecurityJwtService.service;

import com.natya.springSecurityJwtService.dto.JwtRequest;
import com.natya.springSecurityJwtService.dto.RegistrationUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

    ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest);

    ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto);
}
