package com.mastercel.pricing.dto;

public record EstadisticaResponse(
        String marca,
        String modelo,
        String nombreRepuesto,
        Long cantidad
) {
}
