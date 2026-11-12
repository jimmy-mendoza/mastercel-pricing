package com.mastercel.pricing.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastercel.pricing.dto.EstadisticaResponse;
import com.mastercel.pricing.enums.TipoConsulta;
import com.mastercel.pricing.service.EstadisticaService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequiredArgsConstructor
@RequestMapping("/api/estadisticas")
public class EstadisticaController {

    private final EstadisticaService estadisticaService;

    @GetMapping("/mas-consultados")
    public List<EstadisticaResponse> masConsultados(@RequestParam TipoConsulta tipo,
                                                    @RequestParam int anio,
                                                    @RequestParam int mes,
                                                    @RequestParam(defaultValue = "10") int limite) {
        return estadisticaService.masConsultados(tipo, anio, mes, limite);
    }
    
}
