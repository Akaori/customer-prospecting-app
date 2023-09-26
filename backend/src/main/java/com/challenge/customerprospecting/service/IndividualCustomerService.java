package com.challenge.customerprospecting.service;

import com.challenge.customerprospecting.dto.IndividualCustomerPostRequestDTO;
import com.challenge.customerprospecting.entity.IndividualCustomer;

public interface IndividualCustomerService {

    IndividualCustomer save(IndividualCustomerPostRequestDTO individualCustomerPostRequestDTO);
}
