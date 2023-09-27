package com.challenge.customerprospecting.controller;

import com.challenge.customerprospecting.dto.LegalEntityCustomerPostRequestDTO;
import com.challenge.customerprospecting.dto.LegalEntityCustomerPutRequestDTO;
import com.challenge.customerprospecting.entity.LegalEntityCustomer;
import com.challenge.customerprospecting.service.LegalEntityCustomerQueueService;
import com.challenge.customerprospecting.service.LegalEntitycustomerService;
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
@RequestMapping(value = "/api/v1/legal-entity-customers", produces = "application/json")
public class LegalEntityCustomerController {
    private final LegalEntitycustomerService legalEntitycustomerService;
    private final LegalEntityCustomerQueueService legalEntityCustomerQueueService;

    @GetMapping
    public ResponseEntity<List<LegalEntityCustomer>> findAll() {
        return ResponseEntity.ok().body(legalEntitycustomerService.findAll());
    }

    @PostMapping
    public ResponseEntity<LegalEntityCustomer> save(@RequestBody @Valid LegalEntityCustomerPostRequestDTO legalEntityCustomer) {
        return new ResponseEntity<LegalEntityCustomer>(legalEntitycustomerService.save(legalEntityCustomer), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LegalEntityCustomer> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(legalEntitycustomerService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LegalEntityCustomer> update(@PathVariable Long id, @RequestBody LegalEntityCustomerPutRequestDTO legalEntityCustomerPutRequestDTO) {
        return ResponseEntity.ok(legalEntitycustomerService.update(legalEntityCustomerPutRequestDTO, id));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        legalEntitycustomerService.delete(id);
    }

    @GetMapping("/next")
    public ResponseEntity<LegalEntityCustomer> getNextCustomer() {
        // Dequeue the next customer from the service queue
        LegalEntityCustomer nextCustomer = legalEntityCustomerQueueService.dequeueCustomer();
        if (nextCustomer == null) {
            // If there is no customer in the queue, return a 204 No Content status
            return ResponseEntity.noContent().build();
        } else {
            // If there is a customer in the queue, return a 200 OK status and the customer in the body
            return ResponseEntity.ok(nextCustomer);
        }
    }
}
