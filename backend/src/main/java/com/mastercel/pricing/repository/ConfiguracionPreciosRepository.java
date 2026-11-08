package com.mastercel.pricing.repository;

import com.mastercel.pricing.entity.ConfiguracionPrecios;
import com.mastercel.pricing.enums.TipoConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ConfiguracionPreciosRepository extends JpaRepository<ConfiguracionPrecios, Long> {

    @Query("""
        SELECT c FROM ConfiguracionPrecios c
        WHERE c.tipo = :tipo
          AND c.costoDesde <= :costo
          AND (c.costoHasta IS NULL OR c.costoHasta > :costo)
        """)
    Optional<ConfiguracionPrecios> buscarTramo(@Param("tipo") TipoConsulta tipo,
                                               @Param("costo") BigDecimal costo);
}
