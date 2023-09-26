package com.challenge.customerprospecting.service.impl;

import com.challenge.customerprospecting.dto.IndividualCustomerPostRequestDTO;
import com.challenge.customerprospecting.entity.IndividualCustomer;
import com.challenge.customerprospecting.repository.IndividualCustomerRepository;
import com.challenge.customerprospecting.service.IndividualCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class IndividualCustomerServiceImpl implements IndividualCustomerService {

    final IndividualCustomerRepository individualCustomerRepository;
    @Override
    public IndividualCustomer save(IndividualCustomerPostRequestDTO individualCustomerPostRequestDTO) {
        return individualCustomerRepository.save(new IndividualCustomer(individualCustomerPostRequestDTO));
    }
}
