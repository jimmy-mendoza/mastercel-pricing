package com.mastercel.pricing.mapper;

import com.mastercel.pricing.dto.ProveedorRequest;
import com.mastercel.pricing.dto.ProveedorResponse;
import com.mastercel.pricing.entity.Proveedores;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public Proveedores toEntity(ProveedorRequest request) {
        return Proveedores.builder()
                .nombre(request.nombre().trim())
                .ubicacion(request.ubicacion().trim())
                .build();
    }

    public ProveedorResponse toResponse(Proveedores proveedor) {
        return new ProveedorResponse(
                proveedor.getId(),
                proveedor.getNombre(),
                proveedor.getUbicacion()
        );
    }

}
