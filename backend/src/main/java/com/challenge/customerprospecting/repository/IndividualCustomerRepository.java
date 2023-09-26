package com.challenge.customerprospecting.repository;

import com.challenge.customerprospecting.entity.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Long>  {
}
