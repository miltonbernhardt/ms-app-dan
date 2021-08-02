package com.brikton.labapps.msproducto.repository;

import com.brikton.labapps.msproducto.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Material, Integer> {
    @Query("select m from Material m where :stockMinimo <= m.stockActual AND m.stockActual <= :stockMaximo")
    List<Material> getMaterialPorRangoStock(@Param("stockMinimo") Integer stockMinimo, @Param("stockMaximo") Integer stockMaximo);

    @Query("select m from Material m where m.nombre = :nombre")
    List<Material> getMaterialPorNombre(@Param("nombre") String nombre);

    @Query("select m from Material m where m.precio = :precio")
    List<Material> getMaterialPorPrecio(@Param("precio") Double precio);
}
 