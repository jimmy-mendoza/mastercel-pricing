package com.mastercel.pricing.controller;

import com.mastercel.pricing.dto.ConsultaCalculoResponse;
import com.mastercel.pricing.dto.ConsultaRequest;
import com.mastercel.pricing.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping("/calcular")
    public ConsultaCalculoResponse calcular(@Valid @RequestBody ConsultaRequest request) {
        return consultaService.calcular(request);
    }
}
