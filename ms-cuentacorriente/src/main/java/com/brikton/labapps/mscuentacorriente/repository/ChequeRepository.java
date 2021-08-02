package com.brikton.labapps.mscuentacorriente.repository;

import com.brikton.labapps.mscuentacorriente.domain.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeRepository extends JpaRepository<Cheque, Integer> {

}
