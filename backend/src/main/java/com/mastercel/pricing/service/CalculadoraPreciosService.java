package com.mastercel.pricing.service;

import com.mastercel.pricing.dto.PreciosCalculados;
import com.mastercel.pricing.entity.ConfiguracionPrecios;
import com.mastercel.pricing.enums.TipoConsulta;
import com.mastercel.pricing.repository.ConfiguracionPreciosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CalculadoraPreciosService {


    private static final BigDecimal CIEN = new BigDecimal("100");
    private static final BigDecimal MULTIPLO = new BigDecimal("25");

    private final ConfiguracionPreciosRepository configuracionPreciosRepository;

    @Transactional(readOnly = true)
    public PreciosCalculados calcular(TipoConsulta tipo,BigDecimal costo) {
        ConfiguracionPrecios tramo = configuracionPreciosRepository.buscarTramo(tipo,costo)
                .orElseThrow(() -> new RuntimeException("No hay configuración de precios para " + tipo + "con costo "+ costo));

        return new PreciosCalculados(
                aplicar(costo, tramo.getPorcentajeClienteGarantia()),
                aplicar(costo, tramo.getPorcentajeClienteSinGarantia()),
                aplicar(costo, tramo.getPorcentajeMayoristaGarantia()),
                aplicar(costo, tramo.getPorcentajeMayoristaSinGarantia())
        );
    }

    // calcula el costo y porcentaje y luego redonde al multiplo de 25 mas cercano
    private BigDecimal aplicar(BigDecimal costo, BigDecimal porcentaje) {
        BigDecimal factor = BigDecimal.ONE.add(porcentaje.divide(CIEN, 4, RoundingMode.HALF_UP ));
        BigDecimal bruto = costo.multiply(factor);
        return bruto.divide(MULTIPLO, 0, RoundingMode.HALF_UP).multiply(MULTIPLO);
    }

}
