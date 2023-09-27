package com.challenge.customerprospecting.controller;


import com.challenge.customerprospecting.dto.IndividualCustomerPostRequestDTO;
import com.challenge.customerprospecting.dto.IndividualCustomerPutRequestDTO;
import com.challenge.customerprospecting.entity.IndividualCustomer;
import com.challenge.customerprospecting.service.IndividualCustomerQueueService;
import com.challenge.customerprospecting.service.IndividualCustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/individual-customers", produces = "application/json")
public class IndividualCustomerController {

    private final IndividualCustomerService individualCustomerService;
    private final IndividualCustomerQueueService individualCustomerQueueService;

    @GetMapping
    public ResponseEntity<List<IndividualCustomer>> findAll() {
        return ResponseEntity.ok().body(individualCustomerService.findAll());
    }

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

    @GetMapping("/next")
    public ResponseEntity<IndividualCustomer> getNextCustomer() {
        // Dequeue the next customer from the service queue
        IndividualCustomer nextCustomer = individualCustomerQueueService.dequeueCustomer();
        if (nextCustomer == null) {
            // If there is no customer in the queue, return a 204 No Content status
            return ResponseEntity.noContent().build();
        } else {
            // If there is a customer in the queue, return a 200 OK status and the customer in the body
            return ResponseEntity.ok(nextCustomer);
        }
    }
}
