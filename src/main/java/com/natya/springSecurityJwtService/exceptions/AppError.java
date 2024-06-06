package com.natya.springSecurityJwtService.exceptions;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AppError(
        int status,
        String message,
        LocalDateTime dateTime
) {
}
