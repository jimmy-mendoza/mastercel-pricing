package com.mastercel.pricing.repository;

import com.mastercel.pricing.entity.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedores, Long> {

   boolean existsByNombreIgnoreCase(String nombre);

   @Query(value = "SELECT COUNT(*) FROM proveedores WHERE LOWER(nombre) = LOWER(:nombre)",
           nativeQuery = true)
   long contarPorNombre(@Param("nombre") String nombre);

   @Query(value = "SELECT COUNT(*) FROM proveedores WHERE LOWER(nombre) = LOWER(:nombre) AND id <> :id",
           nativeQuery = true)
   long contarPorNombreExcluyendoId(@Param("nombre") String nombre, @Param("id") Long id);
}
