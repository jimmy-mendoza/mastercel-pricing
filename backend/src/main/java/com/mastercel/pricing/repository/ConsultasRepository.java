package com.mastercel.pricing.repository;

import com.mastercel.pricing.dto.ConsultaResponse;
import com.mastercel.pricing.dto.EstadisticaResponse;
import com.mastercel.pricing.entity.Consultas;
import com.mastercel.pricing.enums.TipoConsulta;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultasRepository extends JpaRepository<Consultas, Long> {

    @EntityGraph(attributePaths = "proveedor")
    List<Consultas> findByTipoOrderByFechaDesc(TipoConsulta tipo);

    @EntityGraph(attributePaths = "proveedor")
    List<Consultas> findByTipoAndModeloContainingIgnoreCaseOrderByFechaDesc(TipoConsulta tipo, String modelo);

    boolean existsByProveedorId(Long proveedorId);

    @Query("""
        SELECT new com.mastercel.pricing.dto.EstadisticaResponse(
            c.marca, c.modelo, c.nombreRepuesto, COUNT(c))
        FROM Consultas c
        WHERE c.tipo = :tipo
          AND YEAR(c.fecha) = :anio
          AND MONTH(c.fecha) = :mes
        GROUP BY c.marca, c.modelo, c.nombreRepuesto
        ORDER BY COUNT(c) DESC, c.modelo ASC
        """)
    List<EstadisticaResponse> masConsultados(@Param("tipo") TipoConsulta tipo,
                                             @Param("anio") int anio,
                                             @Param("mes") int mes,
                                             Pageable pageable);
}
