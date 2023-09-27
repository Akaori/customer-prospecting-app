package com.challenge.customerprospecting.service.impl;

import com.challenge.customerprospecting.dto.LegalEntityCustomerPutRequestDTO;
import com.challenge.customerprospecting.service.LegalEntityCustomerQueueService;
import com.challenge.customerprospecting.service.exceptions.LegalEntityCustomerNotFoundException;
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

    private final LegalEntityCustomerRepository legalEntityCustomerRepository;
    private final LegalEntityCustomerQueueService legalEntityCustomerQueueService;


    @Override
    public List<LegalEntityCustomer> findAll() {
        return legalEntityCustomerRepository.findAll();
    }

    @Override
    public LegalEntityCustomer save(LegalEntityCustomerPostRequestDTO legalEntityCustomerPostRequestDTO) {
        // Check if customer cpf already exists in database
        this.checkIfCustomerAlreadyExists(legalEntityCustomerPostRequestDTO.getCnpj());

        // Save customer
        LegalEntityCustomer savedCustomer = legalEntityCustomerRepository.save(new LegalEntityCustomer(legalEntityCustomerPostRequestDTO));

        // Enqueue the saved customer to the service queue
        legalEntityCustomerQueueService.enqueueCustomer(savedCustomer);

        return savedCustomer;
    }

    @Override
    public LegalEntityCustomer findById(Long id) {
        return legalEntityCustomerRepository.findById(id).orElseThrow(() -> new LegalEntityCustomerNotFoundException(id));
    }

    @Override
    public LegalEntityCustomer update(LegalEntityCustomerPutRequestDTO legalEntityCustomerPutRequestDTO, Long id) {
        this.findById(id);

        // Update customer
        LegalEntityCustomer updatedCustomer = legalEntityCustomerRepository.save(new LegalEntityCustomer(legalEntityCustomerPutRequestDTO));

        // Enqueue the updated customer to the service queue
        legalEntityCustomerQueueService.enqueueCustomer(updatedCustomer);

        return updatedCustomer;
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        legalEntityCustomerRepository.deleteById(id);
    }


    public void checkIfCustomerAlreadyExists(String cnpj) {
        List<LegalEntityCustomer> existentCustomer = legalEntityCustomerRepository.findByCnpj(cnpj);
        if (existentCustomer.size() > 0) {
            throw new LegalEntityCustomerAlreadyExistsException(cnpj);
        }
    }
}
