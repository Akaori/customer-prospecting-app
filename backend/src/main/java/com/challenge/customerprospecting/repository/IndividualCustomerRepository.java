package com.challenge.customerprospecting.repository;

import com.challenge.customerprospecting.entity.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Long>  {
    List<IndividualCustomer> findByCpf(String cpf);
}
