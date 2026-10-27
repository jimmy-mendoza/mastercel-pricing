package com.mastercel.pricing.entity;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "proveedores")
@SQLDelete(sql = "UPDATE proveedores SET estado = false WHERE id = ?")
@SQLRestriction("estado = true")
public class Proveedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ubicacion;

    @Builder.Default
    private Boolean estado = true;
}
