package com.challenge.customerprospecting.service;


import com.challenge.customerprospecting.entity.IndividualCustomer;

public interface IndividualCustomerQueueService {
    void enqueueCustomer(IndividualCustomer customer);

    IndividualCustomer dequeueCustomer();

    IndividualCustomer peekCustomer();

    boolean isEmpty();
}
