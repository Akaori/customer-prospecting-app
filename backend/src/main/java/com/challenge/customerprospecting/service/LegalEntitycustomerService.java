package com.challenge.customerprospecting.service;

import com.challenge.customerprospecting.dto.LegalEntityCustomerPostRequestDTO;
import com.challenge.customerprospecting.entity.LegalEntityCustomer;

public interface LegalEntitycustomerService {
    LegalEntityCustomer save(LegalEntityCustomerPostRequestDTO legalEntityCustomerPostRequestDTO);
}
