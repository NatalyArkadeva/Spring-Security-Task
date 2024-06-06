package com.natya.springSecurityJwtService.dto;

import lombok.Builder;

@Builder
public record RegistrationUserDto(
        String name,
        String password,
        String confirmPassword,
        String email
) {
}
