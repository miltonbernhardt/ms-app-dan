package com.brikton.labapps.mscuentacorriente.repository;

import com.brikton.labapps.mscuentacorriente.domain.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

    @Query("select p from Pago p where p.cliente.cuit = :cuit")
    List<Pago> getAllPagosCliente(@Param("cuit") String cuit);
}
