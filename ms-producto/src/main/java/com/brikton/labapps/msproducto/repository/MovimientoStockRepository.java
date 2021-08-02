package com.brikton.labapps.msproducto.repository;

import com.brikton.labapps.msproducto.domain.MovimientoStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoStockRepository extends JpaRepository<MovimientoStock, Integer> {
}