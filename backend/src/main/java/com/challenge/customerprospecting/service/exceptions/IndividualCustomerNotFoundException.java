package com.challenge.customerprospecting.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IndividualCustomerNotFoundException extends RuntimeException {
    public IndividualCustomerNotFoundException(Long id) {
        super("Customer with id " + id  + " not found");
    }

    public IndividualCustomerNotFoundException(Long id, Throwable cause) {
        super("Customer with id " + id  + " not found", cause);
    }
}
