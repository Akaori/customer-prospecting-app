package com.challenge.customerprospecting.repository;

import com.challenge.customerprospecting.entity.LegalEntityCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LegalEntityCustomerRepository  extends JpaRepository<LegalEntityCustomer, Long> {
    List<LegalEntityCustomer> findByCnpj(String cnpj);
}
