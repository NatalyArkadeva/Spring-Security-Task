package com.natya.springSecurityJwtService.dto;

import com.natya.springSecurityJwtService.model.RoleType;
import lombok.Builder;

@Builder
public record UserDto(
        String name,
        String password,
        String email,
        RoleType role
) {
}
