package com.mastercel.pricing.dto;

import com.mastercel.pricing.enums.Calidad;
import com.mastercel.pricing.enums.TipoConsulta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ConsultaResponse(
        Long id,
        TipoConsulta tipo,
        String marca,
        String modelo,
        String nombreRepuesto,
        Calidad calidad,
        Long ProveedorId,
        String ProveedorNombre,
        BigDecimal costo,
        PreciosCalculados precios,
        LocalDateTime fecha
) {
}
