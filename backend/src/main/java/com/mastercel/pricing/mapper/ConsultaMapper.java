package com.mastercel.pricing.mapper;

import com.mastercel.pricing.dto.ConsultaRequest;
import com.mastercel.pricing.dto.ConsultaResponse;
import com.mastercel.pricing.dto.ConsultaResumenResponse;
import com.mastercel.pricing.dto.PreciosCalculados;
import com.mastercel.pricing.entity.Consultas;
import com.mastercel.pricing.entity.Proveedores;
import com.mastercel.pricing.enums.TipoConsulta;
import org.springframework.stereotype.Component;

@Component
public class ConsultaMapper {

    public Consultas toEntity(ConsultaRequest request, Proveedores proveedor, PreciosCalculados precios) {

        String nombreRepuesto = request.tipo() == TipoConsulta.REPUESTO ? request.nombreRepuesto().trim()
                : null;

        return Consultas.builder()
                .tipo(request.tipo())
                .marca(request.marca().trim())
                .modelo(request.modelo().trim())
                .nombreRepuesto(nombreRepuesto)
                .calidad(request.calidad())
                .proveedor(proveedor)
                .costo(request.costo())
                .garantiaExtendida(request.garantiaExtendidaNormalizada())
                .precioClienteGarantia(precios.clienteGarantia())
                .precioClienteSinGarantia(precios.clienteSinGarantia())
                .precioMayoristaGarantia(precios.mayoristaGarantia())
                .precioMayoristaSinGarantia(precios.mayoristaSinGarantia())
                .build();
    }

    public ConsultaResponse toResponse(Consultas consulta) {
        // creando objeto de precios
        PreciosCalculados precios = new PreciosCalculados(
                consulta.getPrecioClienteGarantia(),
                consulta.getPrecioClienteSinGarantia(),
                consulta.getPrecioMayoristaGarantia(),
                consulta.getPrecioMayoristaSinGarantia()
        );
        // creando objeto de consulta
        return new ConsultaResponse(
                consulta.getId(),
                consulta.getTipo(),
                consulta.getMarca(),
                consulta.getModelo(),
                consulta.getNombreRepuesto(),
                consulta.getCalidad(),
                consulta.getProveedor().getId(),
                consulta.getProveedor().getNombre(),
                consulta.getCosto(),
                consulta.getGarantiaExtendida(),
                consulta.getCosto().add(consulta.getGarantiaExtendida()),
                precios, // objeto de precios
                consulta.getFecha()
        );
    }

    public ConsultaResumenResponse toResumen(Consultas consulta) {
            return new ConsultaResumenResponse(
                    consulta.getId(),
                    consulta.getFecha(),
                    consulta.getProveedor().getNombre(),
                    consulta.getModelo(),
                    consulta.getNombreRepuesto(),
                    consulta.getCalidad()
            );
    }
}
