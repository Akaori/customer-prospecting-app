package com.challenge.customerprospecting.service.impl;

import com.challenge.customerprospecting.service.exceptions.LegalEntityCustomerAlreadyExistsException;
import com.challenge.customerprospecting.dto.LegalEntityCustomerPostRequestDTO;
import com.challenge.customerprospecting.entity.LegalEntityCustomer;
import com.challenge.customerprospecting.repository.LegalEntityCustomerRepository;
import com.challenge.customerprospecting.service.LegalEntitycustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LegalEntityCustomerServiceImpl implements LegalEntitycustomerService {

    final LegalEntityCustomerRepository legalEntityCustomerRepository;


    @Override
    public LegalEntityCustomer save(LegalEntityCustomerPostRequestDTO legalEntityCustomerPostRequestDTO) {
        // Check if customer cpf already exists in database
        this.checkIfCustomerAlreadyExists(legalEntityCustomerPostRequestDTO.getCnpj());

        return legalEntityCustomerRepository.save(new LegalEntityCustomer(legalEntityCustomerPostRequestDTO));
    }

    public void checkIfCustomerAlreadyExists(String cnpj) {
        List<LegalEntityCustomer> existentCustomer = legalEntityCustomerRepository.findByCnpj(cnpj);
        if (existentCustomer.size() > 0) {
            throw new LegalEntityCustomerAlreadyExistsException(cnpj);
        }
    }
}
