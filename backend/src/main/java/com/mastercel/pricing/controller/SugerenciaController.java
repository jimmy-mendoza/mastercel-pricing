package com.mastercel.pricing.controller;

import com.mastercel.pricing.dto.SugerenciaModeloResponse;
import com.mastercel.pricing.service.SugerenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sugerencias")
@RequiredArgsConstructor
public class SugerenciaController {

    private final SugerenciaService sugerenciaService;

    @GetMapping("/marcas")
    public List<String> marcas(@RequestParam String texto) {
        return sugerenciaService.sugerirMarcas(texto);
    }

    @GetMapping("/modelos")
    public List<SugerenciaModeloResponse> modelos(@RequestParam String texto,
                                                  @RequestParam(required = false) String marca){
        return sugerenciaService.sugerirModelos(texto,marca);
    }
}
