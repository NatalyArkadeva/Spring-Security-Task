package com.natya.springSecurityJwtService.dto;

import lombok.Builder;

@Builder
public record JwtRequest(
        String name,
        String password
) {
}
