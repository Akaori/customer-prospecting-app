package com.challenge.customerprospecting.service.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IndividualCustomerAlreadyExistsException extends RuntimeException {
    public IndividualCustomerAlreadyExistsException(String cpf) {
        super("Customer with cpf " + cpf + " already exists");
    }

    public IndividualCustomerAlreadyExistsException(String cpf, Throwable cause) {
        super("Customer with cpf " + cpf + " already exists", cause);
    }
}
