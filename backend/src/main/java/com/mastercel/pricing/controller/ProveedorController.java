package com.mastercel.pricing.controller;

import com.mastercel.pricing.dto.MessageResponse;
import com.mastercel.pricing.dto.ProveedorRequest;
import com.mastercel.pricing.dto.ProveedorResponse;
import com.mastercel.pricing.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @PostMapping
    public ResponseEntity<ProveedorResponse> crear(@RequestBody ProveedorRequest proveedorRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.crear(proveedorRequest));
    }

    @GetMapping
    public List<ProveedorResponse> listar(){
        return proveedorService.lisar();
    }

    @GetMapping("/{id}")
    public ProveedorResponse obtenerPorId(@PathVariable Long id){
        return proveedorService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public ProveedorResponse actualizar(@PathVariable Long id, @RequestBody ProveedorRequest request) {
        return proveedorService.actualizar(request, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> eliminar(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return ResponseEntity.ok(new MessageResponse("Proveedor eliminado"));
    }
}
