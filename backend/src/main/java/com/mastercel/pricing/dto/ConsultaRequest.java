package com.mastercel.pricing.dto;

import com.mastercel.pricing.entity.Proveedores;
import com.mastercel.pricing.enums.Calidad;
import com.mastercel.pricing.enums.TipoConsulta;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ConsultaRequest(

        @NotNull(message = "El tipo es obligatorio (PANTALLA O REPUESTO)")
        TipoConsulta tipo,

        @NotBlank(message = "La marca es obligatoria")
        @Size(max = 50 , message = "La marca no puede pasar de 50 caracteres")
        String marca,

        @NotBlank(message = "El modelo es obligatorio")
        @Size(max = 100, message = "El modelo no puede pasar de 100 caracteres")
        String modelo,

        @Size(max = 100 , message = "El nombre no puede pasar de 100 caracteres")
        String nombreRepuesto,

        @NotNull(message = "La calidad es obligatoria")
        Calidad calidad,

        @NotNull(message = "El proveedor es obligatorio")
        Long proveedorId,

        @NotNull(message = "El costo es obligatorio")
        @DecimalMin(value = "0.1" , message = "el costo debe ser mayor a 0")
        @Digits(integer = 8 , fraction = 2 , message = "El costo admite hasta 8 enteros y 2 decimales")
        BigDecimal costo
        ) {
}
