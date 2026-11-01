package com.mastercel.pricing.dto;

import com.mastercel.pricing.enums.Calidad;
import com.mastercel.pricing.enums.TipoConsulta;

import java.math.BigDecimal;

public record ConsultaCalculoResponse(
        TipoConsulta tipo,
        String marca,
        String modelo,
        String nombreRepuesto,
        Calidad calidad,
        BigDecimal costo,
        PreciosCalculados precios
) {
}
