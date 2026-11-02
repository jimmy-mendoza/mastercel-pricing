package com.mastercel.pricing.dto;

import java.math.BigDecimal;

public record PreciosCalculados(
        BigDecimal clienteGarantia,
        BigDecimal clienteSinGarantia,
        BigDecimal mayoristaGarantia,
        BigDecimal mayoristaSinGarantia
) {
}
