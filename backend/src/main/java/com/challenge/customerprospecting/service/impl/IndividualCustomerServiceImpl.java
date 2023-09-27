package com.challenge.customerprospecting.service.impl;

import com.challenge.customerprospecting.dto.IndividualCustomerPostRequestDTO;
import com.challenge.customerprospecting.dto.IndividualCustomerPutRequestDTO;
import com.challenge.customerprospecting.entity.IndividualCustomer;
import com.challenge.customerprospecting.repository.IndividualCustomerRepository;
import com.challenge.customerprospecting.service.IndividualCustomerQueueService;
import com.challenge.customerprospecting.service.IndividualCustomerService;
import com.challenge.customerprospecting.service.exceptions.IndividualCustomerAlreadyExistsException;
import com.challenge.customerprospecting.service.exceptions.IndividualCustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class IndividualCustomerServiceImpl implements IndividualCustomerService {

    final IndividualCustomerRepository individualCustomerRepository;
    final IndividualCustomerQueueService individualCustomerQueueService;

    @Override
    public List<IndividualCustomer> findAll() {
        return individualCustomerRepository.findAll();
    }

    @Override
    public IndividualCustomer save(IndividualCustomerPostRequestDTO individualCustomerPostRequestDTO) {
        // Check if customer cpf already exists in database
        this.checkIfCustomerAlreadyExists(individualCustomerPostRequestDTO.getCpf());

        // Save customer
        IndividualCustomer savedCustomer = individualCustomerRepository.save(new IndividualCustomer(individualCustomerPostRequestDTO));

        // Enqueue the saved customer to the service queue
        individualCustomerQueueService.enqueueCustomer(savedCustomer);

        return savedCustomer;
    }

    @Override
    public IndividualCustomer findById(Long id) {
        return individualCustomerRepository.findById(id).orElseThrow(() -> new IndividualCustomerNotFoundException(id));
    }

    @Override
    public IndividualCustomer update(IndividualCustomerPutRequestDTO individualCustomerPutRequestDTO, Long id) {
        this.findById(id);

        // Update customer
        IndividualCustomer updatedCustomer = individualCustomerRepository.save(new IndividualCustomer(individualCustomerPutRequestDTO));

        // Enqueue the updated customer to the service queue
        individualCustomerQueueService.enqueueCustomer(updatedCustomer);

        return updatedCustomer;
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        individualCustomerRepository.deleteById(id);
    }

    public void checkIfCustomerAlreadyExists(String cpf) {
        List<IndividualCustomer> existentCustomer = individualCustomerRepository.findByCpf(cpf);
        if (existentCustomer.size() > 0) {
            throw new IndividualCustomerAlreadyExistsException(cpf);
        }
    }
}
