package com.challenge.customerprospecting.config;


import com.challenge.customerprospecting.service.exceptions.IndividualCustomerAlreadyExistsException;
import com.challenge.customerprospecting.service.exceptions.IndividualCustomerNotFoundException;
import com.challenge.customerprospecting.service.exceptions.LegalEntityCustomerAlreadyExistsException;
import com.challenge.customerprospecting.service.exceptions.LegalEntityCustomerNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IndividualCustomerAlreadyExistsException.class)
    public ResponseEntity<Map<String, List<String>>> handleIndividualCustomerNotFoundException(IndividualCustomerAlreadyExistsException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IndividualCustomerNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleIndividualCustomerNotFoundException(IndividualCustomerNotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LegalEntityCustomerAlreadyExistsException.class)
    public ResponseEntity<Map<String, List<String>>> handleLegalEntityCustomerAlreadyExistsException(LegalEntityCustomerAlreadyExistsException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LegalEntityCustomerNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleLegalEntityCustomerNotFoundException(LegalEntityCustomerNotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, List<String>>> handleGeneralExceptions(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Map<String, List<String>>> handleRuntimeExceptions(RuntimeException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}