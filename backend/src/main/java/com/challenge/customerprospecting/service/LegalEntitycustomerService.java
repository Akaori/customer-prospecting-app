package com.challenge.customerprospecting.service;

import com.challenge.customerprospecting.dto.IndividualCustomerPutRequestDTO;
import com.challenge.customerprospecting.dto.LegalEntityCustomerPostRequestDTO;
import com.challenge.customerprospecting.dto.LegalEntityCustomerPutRequestDTO;
import com.challenge.customerprospecting.entity.IndividualCustomer;
import com.challenge.customerprospecting.entity.LegalEntityCustomer;

import java.util.List;

public interface LegalEntitycustomerService {
    LegalEntityCustomer save(LegalEntityCustomerPostRequestDTO legalEntityCustomerPostRequestDTO);

    LegalEntityCustomer findById(Long id);

    LegalEntityCustomer update(LegalEntityCustomerPutRequestDTO legalEntityCustomerPutRequestDTO, Long id);

}
