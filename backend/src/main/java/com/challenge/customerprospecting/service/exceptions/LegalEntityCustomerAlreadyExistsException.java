package com.challenge.customerprospecting.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LegalEntityCustomerAlreadyExistsException extends RuntimeException {
    public LegalEntityCustomerAlreadyExistsException(String cnpj) {
        super("Customer with cnpj " + cnpj + " already exists");
    }

    public LegalEntityCustomerAlreadyExistsException(String cnpj, Throwable cause) {
        super("Customer with cnpj " + cnpj + " already exists", cause);
    }
}
