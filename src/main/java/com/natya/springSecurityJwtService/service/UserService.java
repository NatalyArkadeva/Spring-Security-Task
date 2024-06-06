package com.natya.springSecurityJwtService.service;

import com.natya.springSecurityJwtService.dto.RegistrationUserDto;
import com.natya.springSecurityJwtService.dto.UserDto;

public interface UserService {

    UserDto createNewUser(RegistrationUserDto registrationUserDto);
}
