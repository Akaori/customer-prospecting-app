package com.challenge.customerprospecting.controller;

import com.challenge.customerprospecting.dto.LegalEntityCustomerPostRequestDTO;
import com.challenge.customerprospecting.entity.LegalEntityCustomer;
import com.challenge.customerprospecting.service.LegalEntitycustomerService;
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
@RequestMapping(value = "/api/v1/legal-entity-customers", produces = "application/json")
public class LegalEntityCustomerController {
    private final LegalEntitycustomerService legalEntitycustomerService;

    @PostMapping
    public ResponseEntity<LegalEntityCustomer> save(@RequestBody @Valid LegalEntityCustomerPostRequestDTO legalEntityCustomer) {
        return new ResponseEntity<LegalEntityCustomer>(legalEntitycustomerService.save(legalEntityCustomer), HttpStatus.CREATED);
    }
}
