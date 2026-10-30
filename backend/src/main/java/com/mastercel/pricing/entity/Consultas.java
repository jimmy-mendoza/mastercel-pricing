package com.mastercel.pricing.entity;

import com.mastercel.pricing.enums.Calidad;
import com.mastercel.pricing.enums.TipoConsulta;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "consultas")
@SQLDelete(sql = "UPDATE consultas SET estado = false WHERE id = ?")
@SQLRestriction("estado = true")
public class Consultas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoConsulta tipo;

    private String marca;
    private String modelo;
    private String nombreRepuesto;

    @Enumerated(EnumType.STRING)
    private Calidad calidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedores proveedor;

    private BigDecimal costo;
    private BigDecimal precioClienteGarantia;
    private BigDecimal precioClienteSinGarantia;
    private BigDecimal precioMayoristaGarantia;
    private BigDecimal precioMayoristaSinGarantia;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fecha;

    // por defecto sera true sin agregar estado
    @Builder.Default
    private Boolean estado = true;
}