package com.mastercel.pricing.entity;

import com.mastercel.pricing.enums.TipoConsulta;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "configuracion_precios")
public class ConfiguracionPrecios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoConsulta tipo;

    @Column(precision = 10, scale = 2)
    private BigDecimal costoDesde;

    @Column(precision = 10, scale = 2)
    private BigDecimal costoHasta;

    @Column(precision = 6, scale = 2)
    private BigDecimal porcentajeClienteGarantia;

    @Column(precision = 6, scale = 2)
    private BigDecimal porcentajeClienteSinGarantia;

    @Column(precision = 6, scale = 2)
    private BigDecimal porcentajeMayoristaGarantia;

    @Column(precision = 6, scale = 2)
    private BigDecimal porcentajeMayoristaSinGarantia;
}
