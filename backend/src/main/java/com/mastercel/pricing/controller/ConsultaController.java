package com.mastercel.pricing.controller;

import com.mastercel.pricing.dto.*;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> eliminar (@PathVariable Long id){
        consultaService.eliminar(id);
        return ResponseEntity.ok(new MessageResponse("Consulta eliminada correctamente"));
    }

    @PutMapping("/actualizar/{id}")
    public ConsultaResponse actualizar(@Valid @RequestBody ConsultaRequest consultaRequest,
                                                      @PathVariable Long id) {
        return consultaService.actualizar(consultaRequest,id);
    }
}
