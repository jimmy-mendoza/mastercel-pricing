package com.mastercel.pricing.dto;

import com.mastercel.pricing.enums.Calidad;

import java.time.LocalDateTime;

public record ConsultaResumenResponse(
        Long id,
        LocalDateTime fecha,
        String proveedorNombre,
        String modelo,
        String nombreRepuesto,
        Calidad calidad
) {
}
