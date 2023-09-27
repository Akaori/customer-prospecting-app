package com.challenge.customerprospecting.controller;


import com.challenge.customerprospecting.dto.IndividualCustomerPostRequestDTO;
import com.challenge.customerprospecting.dto.IndividualCustomerPutRequestDTO;
import com.challenge.customerprospecting.entity.IndividualCustomer;
import com.challenge.customerprospecting.service.IndividualCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping(value = "/{id}")
    public ResponseEntity<IndividualCustomer> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(individualCustomerService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IndividualCustomer> update(@PathVariable Long id, @RequestBody IndividualCustomerPutRequestDTO individualCustomerPutRequestDTO) {
        return ResponseEntity.ok(individualCustomerService.update(individualCustomerPutRequestDTO, id));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        individualCustomerService.delete(id);
    }
}
