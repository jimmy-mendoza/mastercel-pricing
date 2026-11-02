package com.mastercel.pricing.controller;

import com.mastercel.pricing.dto.ConsultaCalculoResponse;
import com.mastercel.pricing.dto.ConsultaRequest;
import com.mastercel.pricing.dto.ConsultaResponse;
import com.mastercel.pricing.dto.ConsultaResumenResponse;
import com.mastercel.pricing.enums.TipoConsulta;
import com.mastercel.pricing.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping("/calcular")
    public ConsultaCalculoResponse calcular(@Valid @RequestBody ConsultaRequest request) {
        return consultaService.calcular(request);
    }

    @PostMapping
    public ResponseEntity<ConsultaResponse> guardar(@Valid @RequestBody ConsultaRequest request) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(consultaService.guardar(request));
    }

    @GetMapping("/{id}")
    public ConsultaResponse obtenerPorId(@PathVariable Long id) {
        return  consultaService.ObtenerPorId(id);
    }

    @GetMapping
    public List<ConsultaResumenResponse> listar(@RequestParam TipoConsulta tipo,
                                                @RequestParam(required = false) String modelo) {
        return consultaService.listar(tipo, modelo);
    }
}
