package com.mastercel.pricing.repository;

import com.mastercel.pricing.entity.CatalogoDispositivo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogoDispositivosRepository extends JpaRepository<CatalogoDispositivo,Long> {
    @Query("""
            SELECT DISTINCT d.marca FROM CatalogoDispositivo d
            WHERE LOWER(d.marca) LIKE LOWER(CONCAT('%', :texto, '%'))
            ORDER BY d.marca ASC
            """)
    List<String> sugerirMarcas(@Param("texto") String texto, Pageable pageable);

    @Query("""
            SELECT d FROM CatalogoDispositivo d
            WHERE (LOWER(d.nombreComercial) LIKE LOWER(CONCAT('%', :texto, '%'))
                   OR LOWER(d.codigoModelo) LIKE LOWER(CONCAT('%', :texto, '%')))
              AND (:marca = '' OR LOWER(d.marca) = LOWER(:marca))
            ORDER BY
              CASE WHEN LOWER(d.nombreComercial) LIKE LOWER(CONCAT(:texto, '%'))
                     OR LOWER(d.codigoModelo) LIKE LOWER(CONCAT(:texto, '%'))
                   THEN 0 ELSE 1 END,
              d.nombreComercial ASC
            """)
    List<CatalogoDispositivo> sugerirModelos(@Param("texto") String texto,
                                             @Param("marca") String marca,
                                             Pageable pageable);
}
