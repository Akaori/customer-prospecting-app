package com.challenge.customerprospecting.service.impl;

import com.challenge.customerprospecting.dto.IndividualCustomerPostRequestDTO;
import com.challenge.customerprospecting.entity.IndividualCustomer;
import com.challenge.customerprospecting.repository.IndividualCustomerRepository;
import com.challenge.customerprospecting.service.IndividualCustomerService;
import com.challenge.customerprospecting.service.exceptions.IndividualCustomerAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class IndividualCustomerServiceImpl implements IndividualCustomerService {

    final IndividualCustomerRepository individualCustomerRepository;
    @Override
    public IndividualCustomer save(IndividualCustomerPostRequestDTO individualCustomerPostRequestDTO) {
        // Check if customer cpf already exists in database
        this.checkIfCustomerAlreadyExists(individualCustomerPostRequestDTO.getCpf());

        // Save new customer in database
        IndividualCustomerPostRequestDTO individualCustomer = IndividualCustomerPostRequestDTO.builder()
                                                            .name(individualCustomerPostRequestDTO.getName())
                                                            .mcc(individualCustomerPostRequestDTO.getMcc())
                                                            .cpf(individualCustomerPostRequestDTO.getCpf())
                                                            .email(individualCustomerPostRequestDTO.getEmail())
                                                            .build();
        return individualCustomerRepository.save(new IndividualCustomer(individualCustomer));
    }

    private void checkIfCustomerAlreadyExists(String cpf) {
        List<IndividualCustomer> existentCustomer = individualCustomerRepository.findByCpf(cpf);
        if (existentCustomer.size() > 0) {
            throw new IndividualCustomerAlreadyExistsException(cpf);
        }
    }
}
