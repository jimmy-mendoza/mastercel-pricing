package com.mastercel.pricing.repository;

import com.mastercel.pricing.entity.Consultas;
import com.mastercel.pricing.enums.TipoConsulta;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultasRepository extends JpaRepository<Consultas, Long> {

    @EntityGraph(attributePaths = "proveedor")
    List<Consultas> findByTipoOrderByFechaDesc(TipoConsulta tipo);

    @EntityGraph(attributePaths = "proveedor")
    List<Consultas> findByTipoAndModeloContainingIgnoreCaseOrderByFechaDesc(TipoConsulta tipo, String modelo);

    boolean existsByProveedorId(Long proveedorId);
}
