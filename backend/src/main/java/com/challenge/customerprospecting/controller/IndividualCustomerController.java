package com.challenge.customerprospecting.controller;


import com.challenge.customerprospecting.dto.IndividualCustomerPostRequestDTO;
import com.challenge.customerprospecting.entity.IndividualCustomer;
import com.challenge.customerprospecting.entity.LegalEntityCustomer;
import com.challenge.customerprospecting.service.IndividualCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/individual-customers", produces = "application/json")
public class IndividualCustomerController {

    private final IndividualCustomerService individualCustomerService;

    @PostMapping
    public ResponseEntity<IndividualCustomer> save(@RequestBody @Valid IndividualCustomerPostRequestDTO individualCustomer) {
        return new ResponseEntity<IndividualCustomer>(individualCustomerService.save(individualCustomer), HttpStatus.CREATED);
    }
}
