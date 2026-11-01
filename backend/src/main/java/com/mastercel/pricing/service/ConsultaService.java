package com.mastercel.pricing.service;

import com.mastercel.pricing.dto.ConsultaCalculoResponse;
import com.mastercel.pricing.dto.ConsultaRequest;
import com.mastercel.pricing.dto.PreciosCalculados;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.NotCompliantMBeanException;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final CalculadoraPreciosService calculadoraPreciosService;

    public ConsultaCalculoResponse calcular(ConsultaRequest request) {

        PreciosCalculados precios = calculadoraPreciosService.calcular(request.tipo(), request.costo());

        String nombreRepuesto = request.nombreRepuesto() == null ? null : request.nombreRepuesto().trim();

        return new ConsultaCalculoResponse(
                request.tipo(),
                request.marca().trim(),
                request.modelo().trim(),
                nombreRepuesto,
                request.calidad(),
                request.costo(),
                precios
        );
    }
}
