package com.challenge.customerprospecting.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LegalEntityCustomerNotFoundException extends RuntimeException {
    public LegalEntityCustomerNotFoundException(Long id) {
        super("Customer with id " + id  + " not found");
    }

    public LegalEntityCustomerNotFoundException(Long id, Throwable cause) {
        super("Customer with id " + id  + " not found", cause);
    }
}
