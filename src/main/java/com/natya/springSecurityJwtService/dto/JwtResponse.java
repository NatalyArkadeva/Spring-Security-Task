package com.natya.springSecurityJwtService.dto;

import lombok.Builder;

@Builder
public record JwtResponse(
        String token
) {
}
