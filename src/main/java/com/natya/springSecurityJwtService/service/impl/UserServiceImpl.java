package com.natya.springSecurityJwtService.service.impl;

import com.natya.springSecurityJwtService.dto.RegistrationUserDto;
import com.natya.springSecurityJwtService.dto.UserDto;
import com.natya.springSecurityJwtService.exceptions.UserAlreadyExistException;
import com.natya.springSecurityJwtService.mapper.UserMapper;
import com.natya.springSecurityJwtService.model.RoleType;
import com.natya.springSecurityJwtService.model.User;
import com.natya.springSecurityJwtService.repositry.UserRepository;
import com.natya.springSecurityJwtService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDto createNewUser(RegistrationUserDto registrationUserDto) {
        if (userRepository.existsByEmail(registrationUserDto.email())) {
            throw new UserAlreadyExistException("User already exist");
        }
        User user = User.builder()
                .name(registrationUserDto.name())
                .email(registrationUserDto.email())
                .password(passwordEncoder.encode(registrationUserDto.password()))
                .role(RoleType.USER)
                .build();
        return userMapper.toUserDto(userRepository.save(user));
    }
}
