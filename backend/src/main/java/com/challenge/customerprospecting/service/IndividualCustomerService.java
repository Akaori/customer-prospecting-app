package com.challenge.customerprospecting.service;

import com.challenge.customerprospecting.dto.IndividualCustomerPostRequestDTO;
import com.challenge.customerprospecting.dto.IndividualCustomerPutRequestDTO;
import com.challenge.customerprospecting.entity.IndividualCustomer;

import java.util.List;

public interface IndividualCustomerService {

    IndividualCustomer save(IndividualCustomerPostRequestDTO individualCustomerPostRequestDTO);

    IndividualCustomer findById(Long id);

    IndividualCustomer update(IndividualCustomerPutRequestDTO individualCustomerPutRequestDTO, Long id);

    void delete(Long id);
}
