package com.mastercel.pricing.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String message,
        LocalDateTime fecha
) {
}
